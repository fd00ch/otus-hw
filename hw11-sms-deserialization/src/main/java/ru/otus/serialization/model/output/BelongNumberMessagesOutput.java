package ru.otus.serialization.model.output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record BelongNumberMessagesOutput(
        @JsonProperty("belong_number")
        String belongNumber,
        @JsonProperty("message_contents")
        List<MessageContentOutput> messageContents) {
}
