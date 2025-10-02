package com.example.deepseek;

import java.util.List;

public class ChatResponse {
    List<Choice> choices;

    public static class Choice{
        public Message message;
    }

    public static class Message{
        String role;
        String content;
    }
}
