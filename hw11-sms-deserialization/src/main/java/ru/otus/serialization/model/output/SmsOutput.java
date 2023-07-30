package ru.otus.serialization.model.output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SmsOutput(
        @JsonProperty("chat_sessions")
        List<ChatSessionOutput> chatSessions) {
        public SmsOutput(List<ChatSessionOutput> chatSessions) {
                this.chatSessions = chatSessions;
        }
}


