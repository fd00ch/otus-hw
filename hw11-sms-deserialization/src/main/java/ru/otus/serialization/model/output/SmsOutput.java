package ru.otus.serialization.model.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName("sms_output")
public record SmsOutput(
        @JsonProperty("chat_sessions")
        List<ChatSessionOutput> chatSessions) {
        public SmsOutput(List<ChatSessionOutput> chatSessions) {
                this.chatSessions = chatSessions;
        }
}


