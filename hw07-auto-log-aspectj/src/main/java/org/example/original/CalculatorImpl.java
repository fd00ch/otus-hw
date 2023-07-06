package org.example.original;

import lombok.AllArgsConstructor;
import org.example.aspectj.LogAspectJ;

import java.util.Arrays;

@AllArgsConstructor
public class CalculatorImpl implements Calculator {
    @LogAspectJ
    @Override
    public int sum(int... ints) {
        return Arrays.stream(ints).sum();
    }

}
