package com.synesisit.chat.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final MessageRepository messageRepository;

    public ChatService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message saveMessage(String content, String sender) {
        Message message = new Message(content, sender);
        Message msg = messageRepository.save(message);
        return msg;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}