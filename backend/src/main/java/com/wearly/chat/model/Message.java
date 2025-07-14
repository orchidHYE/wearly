package com.wearly.chat.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import java.time.LocalDateTime;

@Data
@Table("MESSAGE")
public class Message {
    @Id
    @Column("MESSAGE_ID")
    private Long messageId;
    @Column("SENDER_ID")
    private Long senderId;
    @Column("RECEIVER_ID")
    private Long receiverId;
    @Column("CONTENT")
    private String content;
    @Column("IMAGE_URL")
    private String imageUrl;
    @Column("MESSAGE_TYPE")
    private String messageType; // 'TEXT' or 'IMAGE'
    @Column("IS_READ")
    private Boolean isRead;
    @Column("CREATED_AT")
    private LocalDateTime createdAt;
} 