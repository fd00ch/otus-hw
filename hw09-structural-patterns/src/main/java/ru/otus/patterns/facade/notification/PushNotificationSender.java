package ru.otus.patterns.facade.notification;

public class PushNotificationSender {
    public void send(String pushNotificationToken, String message) {
        System.out.printf("Send push '%s' to user with token %s%n", message, pushNotificationToken);
    }
}
