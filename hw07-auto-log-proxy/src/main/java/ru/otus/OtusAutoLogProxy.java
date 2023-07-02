package ru.otus;

import ru.otus.original.Calculator;
import ru.otus.original.CalculatorImpl;
import ru.otus.original.StringUtils;
import ru.otus.original.StringUtilsImpl;
import ru.otus.proxygenerator.UniversalProxyGeneratorImpl;

public class OtusAutoLogProxy {
    public static void main(String[] args) {
        Calculator calculatorProxy =
                (Calculator) new UniversalProxyGeneratorImpl().generateProxy(Calculator.class, new CalculatorImpl());
        calculatorProxy.sum(1, 2);
        calculatorProxy.sum(1, 2, 3);
        calculatorProxy.sum(1, 2, 3, 4);
        calculatorProxy.sum(1, 2, 3, 4, 5);

        StringUtils stringUtilsProxy =
                (StringUtils) new UniversalProxyGeneratorImpl().generateProxy(StringUtils.class, new StringUtilsImpl());
        stringUtilsProxy.concat("Test string", 1, 2, 3, 4, 5);
    }
}
