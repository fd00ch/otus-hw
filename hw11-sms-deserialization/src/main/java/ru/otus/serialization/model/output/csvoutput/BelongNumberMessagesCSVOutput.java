package ru.otus.serialization.model.output.csvoutput;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record BelongNumberMessagesCSVOutput(
        @JsonProperty("chat_identifier")
        String chatIdentifier,
        @JsonProperty("last_array")
        String last,
        @JsonProperty("belong_number")
        String belongNumber,
        @JsonProperty("send_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss", timezone = "Europe/Moscow")
        Date sendDate,
        @JsonProperty("text")
        String text) {
}
