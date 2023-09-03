package ru.otus.exception;

public class ConflictingComponentException extends RuntimeException {
    public ConflictingComponentException(String message) {
        super(message);
    }
}
