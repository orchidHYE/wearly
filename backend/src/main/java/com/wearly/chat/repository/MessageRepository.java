package com.wearly.chat.repository;

import com.wearly.chat.model.Message;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    @Query("SELECT * FROM MESSAGE WHERE (SENDER_ID = :user1 AND RECEIVER_ID = :user2) OR (SENDER_ID = :user2 AND RECEIVER_ID = :user1) ORDER BY CREATED_AT ASC")
    List<Message> findChatHistory(@Param("user1") Long user1, @Param("user2") Long user2);
} 