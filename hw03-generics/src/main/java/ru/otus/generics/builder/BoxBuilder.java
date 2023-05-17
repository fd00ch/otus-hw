package ru.otus.generics.builder;

import ru.otus.generics.model.Apple;
import ru.otus.generics.model.Box;
import ru.otus.generics.model.Orange;

public class BoxBuilder {
    public static Box<Apple> buildAppleBox(int appleCount, int appleWeight) {
        Box<Apple> appleBox = new Box<>();
        for (int i = 0; i < appleCount; i++) {
            appleBox.add(new Apple(appleWeight));
        }
        return appleBox;
    }

    public static Box<Orange> buildOrangeBox(int orangeCount, int orangeWeight) {
        Box<Orange> orangeBox = new Box<>();
        for (int i = 0; i < orangeCount; i++) {
            orangeBox.add(new Orange(orangeWeight));
        }
        return orangeBox;
    }
}
