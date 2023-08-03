package ru.otus.patterns.facade.notification;

public class EmailNotificationSender {
    public void send(String email, String message) {
        System.out.printf("Send message '%s' to email %s%n", message, email);
    }
}
