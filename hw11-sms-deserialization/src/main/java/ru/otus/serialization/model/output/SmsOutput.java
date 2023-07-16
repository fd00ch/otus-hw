package ru.otus.serialization.model.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;

public record SmsOutput(
        @JsonProperty("chat_sessions")
//        @JsonUnwrapped
        List<ChatSessionOutput> chatSessions) {
}
