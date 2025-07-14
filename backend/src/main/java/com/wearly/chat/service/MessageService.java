package com.wearly.chat.service;

import com.wearly.chat.model.Message;
import com.wearly.chat.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public Message saveMessage(Message message) {
        message.setCreatedAt(LocalDateTime.now());
        message.setIsRead(false);
        return messageRepository.save(message);
    }

    public List<Message> getChatHistory(Long user1, Long user2) {
        return messageRepository.findChatHistory(user1, user2);
    }
} 