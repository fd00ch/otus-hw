package ru.otus.serialization.model.output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ChatSessionOutput(
        @JsonProperty("chat_identifier")
        String chatIdentifier,
        @JsonProperty("members")
        List<MemberOutput> members,
        @JsonProperty("messages")
        List<BelongNumberMessagesOutput> messages) {
}
