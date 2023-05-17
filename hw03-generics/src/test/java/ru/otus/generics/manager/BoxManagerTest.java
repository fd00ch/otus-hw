package ru.otus.generics.manager;

import org.junit.jupiter.api.Test;
import ru.otus.generics.builder.BoxBuilder;
import ru.otus.generics.model.Apple;
import ru.otus.generics.model.Box;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoxManagerTest {

    @Test
    void moveContentToAnotherBox() {
        Box<Apple> appleBox1 = BoxBuilder.buildAppleBox(30, 100);
        Box<Apple> appleBox2 = BoxBuilder.buildAppleBox(20, 100);

        BoxManager.moveContentToAnotherBox(appleBox1, appleBox2);

        assertEquals(0, appleBox2.getFruits().size());
        assertEquals(50, appleBox1.getFruits().size());
    }

}