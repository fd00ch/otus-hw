package ru.otus.serialization.model.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SmsInput(
        @JsonProperty("chat_sessions")
        List<ChatSession> chatSessions) {
}
