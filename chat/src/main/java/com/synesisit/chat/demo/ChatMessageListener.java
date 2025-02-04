package com.synesisit.chat.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatMessageListener {
    private final ChatService chatService;
    @KafkaListener(topics = "chat-messages", groupId = "chat-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
        chatService.saveMessage(message, "Kafka");
        for (Message msg : chatService.getAllMessages()) {
            System.out.println(msg);
        }

        // Process the message and store it in NoSQL database
    }
}