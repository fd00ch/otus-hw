package ru.otus.generics;

import org.junit.jupiter.api.Test;
import ru.otus.generics.model.Apple;
import ru.otus.generics.model.Box;
import ru.otus.generics.model.Orange;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoxTest {

    @Test
    void boxWeightTest() {
        Box<Apple> appleBox = new Box<>();
        appleBox.addAll(List.of(new Apple(100.1f), new Apple(100.1f), new Apple(100.1f)));

        Box<Orange> orangeBox = new Box<>();
        orangeBox.addAll(List.of(new Orange(150.15f), new Orange(150.15f)));

        assertEquals(300.3f, orangeBox.weight(), 0.001f);
        assertEquals(300.3f, appleBox.weight(), 0.001f);
    }

    @Test
    void boxCompareTrueTest() {
        Box<Apple> appleBox = new Box<>();
        appleBox.addAll(List.of(new Apple(100.1f), new Apple(100.1f), new Apple(100.1f)
                , new Apple(100.1f), new Apple(100.1f), new Apple(100.1f)
                , new Apple(100.1f), new Apple(100.1f), new Apple(100.1f)
                , new Apple(100.1f), new Apple(100.1f), new Apple(100.1f)
                , new Apple(100.1f), new Apple(100.1f), new Apple(100.1f)
                , new Apple(100.1f), new Apple(100.1f), new Apple(100.1f)));

        Box<Orange> orangeBox = new Box<>();
        orangeBox.addAll(List.of(new Orange(150.15f), new Orange(150.15f)
                , new Orange(150.15f), new Orange(150.15f)
                , new Orange(150.15f), new Orange(150.15f)
                , new Orange(150.15f), new Orange(150.15f)
                , new Orange(150.15f), new Orange(150.15f)
                , new Orange(150.15f), new Orange(150.15f)));
        assertTrue(appleBox.compare(orangeBox));
    }

    @Test
    void boxCompareFalseTest() {
        Box<Apple> appleBox = new Box<>();
        appleBox.addAll(List.of(new Apple(100.0f), new Apple(100.0f), new Apple(100.0f)));

        Box<Orange> orangeBox = new Box<>();
        orangeBox.addAll(List.of(new Orange(150.0f), new Orange(150.0f), new Orange(150.0f)));

        assertNotEquals(orangeBox.weight(), appleBox.weight());
        assertFalse(appleBox.compare(orangeBox));
    }

}
