package org.otus;

import org.otus.model.CustomToStringHelper;
import org.otus.model.Person;

public class AnnotationProcessorTest {
    public static void main(String[] args) {
        Person person = new Person("Marty", "McFly", 25);
        System.out.printf("Person original @ToString: %s%n", person.toString());
        System.out.printf("Person custom @ToString: %s%n", CustomToStringHelper.toCustomString(person));
    }
}