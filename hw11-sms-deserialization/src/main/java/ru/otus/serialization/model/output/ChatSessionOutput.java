package ru.otus.serialization.model.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;

public record ChatSessionOutput(
        @JsonProperty("chat_identifier")
        String chatIdentifier,
        @JsonProperty("members")
//        @JsonUnwrapped
        List<MemberOutput> members,
        @JsonProperty("messages")
//        @JsonUnwrapped
        List<BelongNumberMessagesOutput> messages) {
}
