package org.akimov.exception;

public class BotException extends RuntimeException {
    public BotException(String message, Throwable cause) {
        super(message, cause);
    }
}
