package com.synesisit.backend.demo;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ChatController {
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    public ChatController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @MessageMapping("/chat.sendMessage")
    public void handleChatMessage(@Payload String message) {
        try {
            logger.info("Received message: {}", message);
            kafkaTemplate.send("chat-messages", message);
            logger.info("Message sent to Kafka topic");
        } catch (Exception e) {
            logger.error("Error handling message", e);
        }
    }
}