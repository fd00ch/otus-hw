package ru.otus.serialization.model.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ChatSession(
        @JsonProperty("chat_id")
        int chatId,
        @JsonProperty("chat_identifier")
        String chatIdentifier,
        @JsonProperty("display_name")
        String displayName,
        @JsonProperty("is_deleted")
        boolean isDeleted,
        @JsonProperty("members")
        List<Member> members,
        @JsonProperty("messages")
        List<Message> messages) {

}
