package ru.otus.generics.builder;

import org.junit.jupiter.api.Test;
import ru.otus.generics.model.Apple;
import ru.otus.generics.model.Box;
import ru.otus.generics.model.Orange;
import static org.junit.jupiter.api.Assertions.*;

class BoxBuilderTest {

    @Test
    void buildAppleBox() {
        Box<Apple> appleBox = BoxBuilder.buildAppleBox(3, 100);

        assertEquals(3, appleBox.getFruits().size());
        assertEquals(100, appleBox.getFruits().get(0).getWeight());
        assertEquals(100, appleBox.getFruits().get(1).getWeight());
        assertEquals(100, appleBox.getFruits().get(2).getWeight());
    }

    @Test
    void buildOrangeBox() {
        Box<Orange> orangeBox = BoxBuilder.buildOrangeBox(2, 150);

        assertEquals(2, orangeBox.getFruits().size());
        assertEquals(150, orangeBox.getFruits().get(0).getWeight());
        assertEquals(150, orangeBox.getFruits().get(1).getWeight());
    }
}