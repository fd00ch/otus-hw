package ru.otus.patterns.facade;

import ru.otus.patterns.facade.model.Person;
import ru.otus.patterns.facade.notification.EmailNotificationSender;
import ru.otus.patterns.facade.notification.NotificationSenderFacade;
import ru.otus.patterns.facade.notification.PushNotificationSender;
import ru.otus.patterns.facade.notification.SmsNotificationSender;

public class FacadeMain {
    public static void main(String[] args) {
        var person1 = new Person("Doc Brown", "doc.brown@mail.com", "+79999999999",
                "pushtoken1", false, true, false);
        var person2 = new Person("Marty McFly", "marty.chicken@mail.com", "+79888888888",
                "pushtoken2", true, true, true);

        var notificationSenderFacade = new NotificationSenderFacade(
                new EmailNotificationSender(),
                new SmsNotificationSender(),
                new PushNotificationSender()
        );

        notificationSenderFacade.send(person1, "Hello, Doc Brown! It's me, Marty McFly!");
        notificationSenderFacade.send(person2, "Hello, Marty McFly! It's me, Doc Brown!");

}}
