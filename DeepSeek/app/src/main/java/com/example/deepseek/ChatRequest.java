package com.example.deepseek;

import java.util.List;

public class ChatRequest {
    String model;
    List<Message> messages;

    double temp;
    int max_token;

    public ChatRequest(String model, List<Message> messages, double temp, int max_token) {
        this.model = model;
        this.messages = messages;
        this.temp = temp;
        this.max_token = max_token;
    }

    public static class Message{

        // 'role' - либо 'user', либо 'system'
        // либо от сети сообщение, либо от нас
        String role;

        String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }
}
