package ru.otus.test;

import ru.otus.fortesting.Calculator;
import ru.otus.testannotations.After;
import ru.otus.testannotations.Before;
import ru.otus.testannotations.Test;
import static ru.otus.assertions.Assertions.assertEquals;

public class CalculatorTest {
    Calculator calculator;

    @Before
    void before() {
        calculator = new Calculator();
    }

    @After
    void after() {
        calculator = null;
    }

    @Test
    void testMultiplySuccess() {
        assertEquals(20, calculator.multiply(4, 5),
                "Multiplication failed");
    }

    @Test
    void testDivisionSuccess() {
        assertEquals(2, calculator.division(10, 5),
                "Division failed");
    }

    @Test
    void testDivisionFail() {
        // Тест не пройдёт
        assertEquals(2, calculator.division(10, 0),
                "Division failed");
    }

    @Test
    void testSumSuccess() {
        assertEquals(9, calculator.sum(4, 5),
                "Sum failed");
    }

    @Test
    void testSubtractionSuccess() {
        assertEquals(-1, calculator.subtraction(4, 5),
                "Subtraction failed");
    }


}
