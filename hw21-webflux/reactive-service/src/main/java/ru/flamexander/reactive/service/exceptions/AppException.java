package ru.flamexander.reactive.service.exceptions;

public class AppException extends RuntimeException {
    private String message;

    public String getMessage() {
        return message;
    }

    public AppException(String message) {
        this.message = message;
    }
}
