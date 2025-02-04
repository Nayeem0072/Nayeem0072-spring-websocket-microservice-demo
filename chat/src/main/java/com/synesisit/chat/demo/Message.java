package com.synesisit.chat.demo;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;
import java.time.LocalDateTime;

@Document(collection = "messages")
@Data
public class Message {
    @Id
    private String id;
    private String content;
    private String sender;
    @CreatedDate
    private LocalDateTime timestamp;

    public Message(String content, String sender) {
        this.content = content;
        this.sender = sender;
    }
}