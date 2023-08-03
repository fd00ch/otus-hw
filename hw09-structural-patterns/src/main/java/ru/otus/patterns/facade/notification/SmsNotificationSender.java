package ru.otus.patterns.facade.notification;

public class SmsNotificationSender {
    public void send(String phone, String message) {
        System.out.printf("Send sms '%s' to phone number %s%n", message, phone);
    }
}
