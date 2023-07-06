package ru.otus.original;

import lombok.AllArgsConstructor;
import ru.otus.annotations.Log;

import java.util.Arrays;

@AllArgsConstructor
public class CalculatorImpl implements Calculator {
    @Log
    @Override
    public int sum(int... ints) {
        return Arrays.stream(ints).sum();
    }

}
