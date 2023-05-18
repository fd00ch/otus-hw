package ru.otus.generics.manager;

import ru.otus.generics.model.Box;
import ru.otus.generics.model.Fruit;

public class BoxManager {
    public static <T extends Fruit> void moveContentToAnotherBox(Box<? super T> dst, Box<T> src) {
        if (dst == src) {
            return;
        }
        dst.addAll(src.getFruits());
        src.getFruits().clear();
    }
}
