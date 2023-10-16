package ru.otus.serialization.model.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record MessageContentOutput(
        @JsonProperty("send_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy'T'hh:mm:ss", timezone = "Europe/Moscow")
        Date sendDate,
        @JsonProperty("text")
        String text) {
}
