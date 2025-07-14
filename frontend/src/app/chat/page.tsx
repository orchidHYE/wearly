"use client";
// @ts-ignore: sockjs-client 타입 선언 없음을 무시
import SockJS from "sockjs-client";
import { useEffect, useRef, useState } from "react";
import { Client } from "@stomp/stompjs";
import axios from "axios";

const WS_URL = process.env.NEXT_PUBLIC_WS_URL || "ws://localhost:8080";
const API_URL = process.env.NEXT_PUBLIC_API_URL || "http://localhost:8080";

export default function ChatPage() {
  const [messages, setMessages] = useState<any[]>([]);
  const [text, setText] = useState("");
  const [imageUrl, setImageUrl] = useState("");
  const [file, setFile] = useState<File | null>(null);
  const [isUploading, setIsUploading] = useState(false);
  const [userId] = useState(1); // 내 ID (테스트용)
  const [targetId] = useState(2); // 상대 ID (테스트용)
  const stompClient = useRef<Client | null>(null);
  const subscriptionRef = useRef<any>(null);
  const messagesEndRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const socket = new SockJS(`${API_URL}/ws-chat`);
    const client = new Client({
      webSocketFactory: () => socket as any,
      onConnect: () => {
        subscriptionRef.current = client.subscribe(
          `/topic/chat.${userId < targetId ? userId + "." + targetId : targetId + "." + userId}`,
          (msg) => {
            const newMsg = JSON.parse(msg.body);
            setMessages((prev) => [...prev, newMsg]);
          }
        );
      },
      onDisconnect: () => {
        stompClient.current = null;
      },
      onStompError: () => {
        stompClient.current = null;
      },
    });
    client.activate();
    stompClient.current = client;

    return () => {
      // cleanup에서만 구독 해제 및 연결 해제
      if (subscriptionRef.current) {
        subscriptionRef.current.unsubscribe();
        subscriptionRef.current = null;
      }
      if (stompClient.current) {
        stompClient.current.deactivate();
        stompClient.current = null;
      }
    };
  }, [userId, targetId]);

  useEffect(() => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages]);

  const handleSend = async () => {
    if (!text.trim() && !imageUrl) return;
    const msg = {
      senderId: userId,
      receiverId: targetId,
      content: text,
      imageUrl: imageUrl || null,
      messageType: imageUrl ? "IMAGE" : "TEXT",
      timestamp: new Date().toISOString(),
    };
    stompClient.current?.publish({
      destination: "/app/chat.send",
      body: JSON.stringify(msg),
    });
    setText("");
    setImageUrl("");
    setFile(null);
  };

  const handleFileChange = async (e: React.ChangeEvent<HTMLInputElement>) => {
    const f = e.target.files?.[0];
    if (!f) return;
    setIsUploading(true);
    const formData = new FormData();
    formData.append("file", f);
    try {
      const res = await axios.post(`${API_URL}/api/messages/image`, formData, {
        headers: { "Content-Type": "multipart/form-data" },
      });
      setImageUrl(res.data);
      setFile(f);
    } catch (err) {
      alert("이미지 업로드 실패");
    } finally {
      setIsUploading(false);
    }
  };

  return (
    <div className="flex flex-col h-screen bg-gray-50">
      <div className="flex-1 overflow-y-auto p-4 space-y-2">
        {messages.map((msg, idx) => (
          <div
            key={idx}
            className={`flex ${msg.senderId === userId ? "justify-end" : "justify-start"}`}
          >
            <div
              className={`max-w-xs rounded-lg px-4 py-2 shadow text-sm whitespace-pre-line
                ${msg.senderId === userId ? "bg-blue-500 text-white" : "bg-white border"}`}
            >
              {msg.content && <div>{msg.content}</div>}
              {msg.imageUrl && (
                <img
                  src={msg.imageUrl}
                  alt="첨부 이미지"
                  className="mt-2 max-w-[180px] max-h-[180px] rounded border"
                />
              )}
              <div className="text-xs text-gray-300 mt-1 text-right">
                {new Date(msg.timestamp).toLocaleTimeString()}
              </div>
            </div>
          </div>
        ))}
        <div ref={messagesEndRef} />
      </div>
      <div className="p-4 border-t bg-white flex gap-2 items-center">
        <input
          type="text"
          className="flex-1 border rounded px-3 py-2 focus:outline-none"
          placeholder="메시지를 입력하세요"
          value={text}
          onChange={e => setText(e.target.value)}
          onKeyUp={e => { if (e.key === "Enter") handleSend(); }}
          disabled={isUploading}
        />
        <label className="cursor-pointer">
          <input
            type="file"
            accept="image/*"
            className="hidden"
            onChange={handleFileChange}
            disabled={isUploading}
          />
          <span className="inline-block px-3 py-2 bg-gray-200 rounded hover:bg-gray-300">
            📎
          </span>
        </label>
        {file && (
          <span className="text-xs text-gray-500 max-w-[100px] truncate">{file.name}</span>
        )}
        <button
          onClick={handleSend}
          className="bg-blue-500 text-white px-4 py-2 rounded disabled:bg-gray-300"
          disabled={isUploading || (!text.trim() && !imageUrl)}
        >
          전송
        </button>
      </div>
    </div>
  );
} 