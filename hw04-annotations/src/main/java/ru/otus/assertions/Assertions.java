package ru.otus.assertions;

public class Assertions {
    public static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message);
        }
    }
}
