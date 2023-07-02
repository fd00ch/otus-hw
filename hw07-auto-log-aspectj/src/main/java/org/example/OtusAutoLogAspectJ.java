package org.example;

import org.example.original.Calculator;
import org.example.original.CalculatorImpl;
import org.example.original.StringUtils;
import org.example.original.StringUtilsImpl;

public class OtusAutoLogAspectJ {
    public static void main(String[] args) {
        Calculator calculator = new CalculatorImpl();
        calculator.sum(1, 2);
        calculator.sum(1, 2, 3);
        calculator.sum(1, 2, 3, 4);
        calculator.sum(1, 2, 3, 4, 5);

        StringUtils stringUtils = new StringUtilsImpl();
        stringUtils.concat("Test string", 1, 2, 3, 4, 5);
    }
}