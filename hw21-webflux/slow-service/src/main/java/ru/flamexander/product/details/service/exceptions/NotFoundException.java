package ru.flamexander.product.details.service.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String className, long id) {
        super("Could not find " + className + ": " + id);
    }
}
