package ru.flamexander.product.details.service.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ErrorDto {
    private int code;
    private String message;
    private LocalDateTime date;

    public ErrorDto(int code, String message) {
        this.code = code;
        this.message = message;
        this.date = LocalDateTime.now();
    }
}
