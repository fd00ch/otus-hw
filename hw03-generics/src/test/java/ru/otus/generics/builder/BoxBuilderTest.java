package ru.otus.generics.builder;

import org.junit.jupiter.api.Test;
import ru.otus.generics.model.Apple;
import ru.otus.generics.model.Box;
import ru.otus.generics.model.Fruit;
import ru.otus.generics.model.Orange;
import static org.junit.jupiter.api.Assertions.*;

class BoxBuilderTest {

    @Test
    void buildAppleBox() {
        Box<Apple> appleBox = BoxBuilder.buildAppleBox(3, 100.1f);

        assertEquals(3, appleBox.getFruits().size());
        assertEquals(100.1f, appleBox.getFruits().get(0).getWeight());
        assertEquals(100.1f, appleBox.getFruits().get(1).getWeight());
        assertEquals(100.1f, appleBox.getFruits().get(2).getWeight());
    }

    @Test
    void buildOrangeBox() {
        Box<Orange> orangeBox = BoxBuilder.buildOrangeBox(2, 150.15f);

        assertEquals(2, orangeBox.getFruits().size());
        assertEquals(150.15f, orangeBox.getFruits().get(0).getWeight());
        assertEquals(150.15f, orangeBox.getFruits().get(1).getWeight());
    }

    @Test
    void buildAppleFruitBox() {
        Box<Fruit> appleFruitBox = BoxBuilder.buildAppleFruitBox(3, 100.1f);

        assertEquals(3, appleFruitBox.getFruits().size());
        assertEquals(100.1f, appleFruitBox.getFruits().get(0).getWeight());
        assertEquals(100.1f, appleFruitBox.getFruits().get(1).getWeight());
        assertEquals(100.1f, appleFruitBox.getFruits().get(2).getWeight());
    }

    @Test
    void buildOrangeFruitBox() {
        Box<Fruit> orangeFruitBox = BoxBuilder.buildOrangeFruitBox(2, 150.15f);

        assertEquals(2, orangeFruitBox.getFruits().size());
        assertEquals(150.15f, orangeFruitBox.getFruits().get(0).getWeight());
        assertEquals(150.15f, orangeFruitBox.getFruits().get(1).getWeight());
    }
}