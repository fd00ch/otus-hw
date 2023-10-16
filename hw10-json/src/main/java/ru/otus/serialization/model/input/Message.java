package ru.otus.serialization.model.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;


public record Message(
        @JsonProperty("ROWID")
        int rowId,
        @JsonProperty("attributedBody")
        String attributedBody,
        @JsonProperty("belong_number")
        String belongNumber,
        @JsonProperty("date")
        long date,
        @JsonProperty("date_read")
        long dateRead,
        @JsonProperty("guid")
        String guid,
        @JsonProperty("handle_id")
        int handleId,
        @JsonProperty("has_dd_results")
        boolean hasDdResults,
        @JsonProperty("is_deleted")
        boolean isDeleted,
        @JsonProperty("is_from_me")
        boolean isFromMe,
        @JsonProperty("send_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss", timezone = "Europe/Moscow")
        Date sendDate,
        @JsonProperty("send_status")
        int sendStatus,
        @JsonProperty("service")
        String service,
        @JsonProperty("text")
        String text) {


}
