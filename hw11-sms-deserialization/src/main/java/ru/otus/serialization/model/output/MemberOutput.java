package ru.otus.serialization.model.output;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MemberOutput(
        @JsonProperty("last")
        String last) {
}
