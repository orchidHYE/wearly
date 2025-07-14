package com.wearly.chat.websocket;

import com.wearly.chat.model.Message;
import com.wearly.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {
    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.send")
    public void sendMessage(@Payload Message message) {
        // 메시지 저장
        Message saved = messageService.saveMessage(message);
        // 1:1 채팅방 구분을 위해 /topic/chat.{user1}.{user2} 사용
        String room = message.getSenderId() < message.getReceiverId() ?
            message.getSenderId() + "." + message.getReceiverId() :
            message.getReceiverId() + "." + message.getSenderId();
        messagingTemplate.convertAndSend("/topic/chat." + room, saved);
    }
} 