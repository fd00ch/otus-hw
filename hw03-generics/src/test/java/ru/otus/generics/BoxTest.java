package ru.otus.generics;

import org.junit.jupiter.api.Test;
import ru.otus.generics.model.Apple;
import ru.otus.generics.model.Box;
import ru.otus.generics.model.Orange;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoxTest {

    @Test
    void boxWeightTest() {
        Box<Apple> appleBox = new Box<>();
        appleBox.addAll(List.of(new Apple(100), new Apple(100), new Apple(100)));

        Box<Orange> orangeBox = new Box<>();
        orangeBox.addAll(List.of(new Orange(150), new Orange(150)));

        assertEquals(300, orangeBox.weight());
        assertEquals(300, appleBox.weight());
    }

    @Test
    void boxCompareTrueTest() {
        Box<Apple> appleBox = new Box<>();
        appleBox.addAll(List.of(new Apple(100), new Apple(100), new Apple(100)));

        Box<Orange> orangeBox = new Box<>();
        orangeBox.addAll(List.of(new Orange(150), new Orange(150)));

        assertEquals(orangeBox.weight(), appleBox.weight());
        assertTrue(appleBox.compare(orangeBox));
    }

    @Test
    void boxCompareFalseTest() {
        Box<Apple> appleBox = new Box<>();
        appleBox.addAll(List.of(new Apple(100), new Apple(100), new Apple(100)));

        Box<Orange> orangeBox = new Box<>();
        orangeBox.addAll(List.of(new Orange(150), new Orange(150), new Orange(150)));

        assertNotEquals(orangeBox.weight(), appleBox.weight());
        assertFalse(appleBox.compare(orangeBox));
    }

}
