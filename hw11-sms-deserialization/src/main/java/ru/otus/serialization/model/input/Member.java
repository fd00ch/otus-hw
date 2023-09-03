package ru.otus.serialization.model.input;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Member(
        @JsonProperty("first")
        String first,
        @JsonProperty("handle_id")
        int handleId,
        @JsonProperty("image_path")
        String imagePath,
        @JsonProperty("last")
        String last,
        @JsonProperty("middle")
        String middle,
        @JsonProperty("phone_number")
        String phoneNumber,
        @JsonProperty("service")
        String service,
        @JsonProperty("thumb_path")
        String thumbPath) {

}
