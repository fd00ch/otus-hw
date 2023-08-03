package ru.otus.patterns.facade.model;

public record Person (
    String name,
    String email,
    String phone,
    String pushNotificationToken,
    boolean sendEmailNotification,
    boolean sendSmsNotification,
    boolean sendPushNotification) {
}
