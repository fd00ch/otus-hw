package ru.otus.serialization.model.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;

public record BelongNumberMessagesOutput(
        @JsonProperty("belong_number")
        String belongNumber,
        @JsonProperty("message_contents")
//        @JsonUnwrapped
        List<MessageContentOutput> messageContents) {
}
