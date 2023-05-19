package ru.otus.generics.manager;

import org.junit.jupiter.api.Test;
import ru.otus.generics.builder.BoxBuilder;
import ru.otus.generics.model.Apple;
import ru.otus.generics.model.Box;
import ru.otus.generics.model.Fruit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoxManagerTest {

    @Test
    void moveContentToAnotherBox() {
        Box<Apple> appleBox1 = BoxBuilder.buildAppleBox(30, 100.1f);
        Box<Apple> appleBox2 = BoxBuilder.buildAppleBox(20, 100.1f);

        BoxManager.moveContentToAnotherBox(appleBox1, appleBox2);

        assertEquals(0, appleBox2.getFruits().size());
        assertEquals(50, appleBox1.getFruits().size());
    }

    @Test
    void moveContentToAnotherFruitBox() {
        Box<Apple> appleBox = BoxBuilder.buildAppleBox(30, 100.1f);
        Box<Fruit> appleFruitBox = BoxBuilder.buildAppleFruitBox(20, 100.1f);

        BoxManager.moveContentToAnotherBox(appleFruitBox, appleBox);

        assertEquals(0, appleBox.getFruits().size());
        assertEquals(50, appleFruitBox.getFruits().size());
    }

    @Test
    void moveContentToSameOneBox() {
        Box<Apple> appleBox1 = BoxBuilder.buildAppleBox(30, 100.1f);

        BoxManager.moveContentToAnotherBox(appleBox1, appleBox1);

        assertEquals(30, appleBox1.getFruits().size());
    }

    @Test
    void moveContentToNullBox() {
        Box<Apple> appleBox1 = BoxBuilder.buildAppleBox(30, 100.1f);

        BoxManager.moveContentToAnotherBox(null, appleBox1);

        assertEquals(30, appleBox1.getFruits().size());
    }

    @Test
    void moveContentFromNullBox() {
        Box<Apple> appleBox1 = BoxBuilder.buildAppleBox(30, 100.1f);

        BoxManager.moveContentToAnotherBox(appleBox1, null);

        assertEquals(30, appleBox1.getFruits().size());
    }

}