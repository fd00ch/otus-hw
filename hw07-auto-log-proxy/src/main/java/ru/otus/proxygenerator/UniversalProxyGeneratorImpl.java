package ru.otus.proxygenerator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class UniversalProxyGeneratorImpl implements UniversalProxyGenerator {

    @Override
    public <I, T extends I> Object generateProxy(Class<I> interfaze, T classImplInstance) {
        InvocationHandler handler = new Handler<>(classImplInstance);
        return Proxy.newProxyInstance(
                interfaze.getClassLoader(),
                new Class<?>[]{interfaze},
                handler
        );
    }
}
