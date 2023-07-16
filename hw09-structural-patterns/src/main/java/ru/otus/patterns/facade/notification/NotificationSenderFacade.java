package ru.otus.patterns.facade.notification;

import ru.otus.patterns.facade.model.Person;

public class NotificationSenderFacade {
    private final EmailNotificationSender emailNotificationSender;
    private final SmsNotificationSender smsNotificationSender;
    private final PushNotificationSender pushNotificationSender;

    public NotificationSenderFacade(EmailNotificationSender emailNotificationSender,
                                    SmsNotificationSender smsNotificationSender,
                                    PushNotificationSender pushNotificationSender) {
        this.emailNotificationSender = emailNotificationSender;
        this.smsNotificationSender = smsNotificationSender;
        this.pushNotificationSender = pushNotificationSender;
    }

    public void send(Person person, String message) {
        if (person.sendEmailNotification()) emailNotificationSender.send(person.email(), message);
        if (person.sendSmsNotification()) smsNotificationSender.send(person.phone(), message);
        if (person.sendPushNotification()) pushNotificationSender.send(person.pushNotificationToken(), message);
    }
}
