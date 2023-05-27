package ru.otus;

import ru.otus.teststarter.TestStarter;

import java.lang.reflect.InvocationTargetException;

public class OtusAnnotations {
    public static void main(String[] args)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TestStarter.startPackageTests("ru.otus.test");
    }
}
