package ru.otus.teststarter;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import ru.otus.testannotations.After;
import ru.otus.testannotations.Before;
import ru.otus.testannotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

public class TestStarter {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TestStarter.class);

    record TestResults(String testClassName, int passed, int failed) { }

    public static void startPackageTests(String packageName)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        var allClassesInTestFolder = findAllClasses(packageName);
        for (var testClass: allClassesInTestFolder) {
            startClassTests(testClass);
        }
    }

    public static void startClassTests(String className)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException, ClassNotFoundException {
        Class<?> testClass = Class.forName(className);
        startClassTests(testClass);
    }

    private static Set<Class<?>> findAllClasses(String packageName) {
        Reflections reflections = new Reflections(packageName, Scanners.SubTypes.filterResultsBy(f -> true));
        return reflections.getSubTypesOf(Object.class);
    }

    private static void startClassTests(Class<?> testClass)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var testClassConstructor = testClass.getConstructor();
        var testClassInstance = testClassConstructor.newInstance();

        var allClassMethods = testClass.getDeclaredMethods();
        var beforeMethods = getMethodsAnnotatedWith(allClassMethods, Before.class);
        var testMethods = getMethodsAnnotatedWith(allClassMethods, Test.class);
        var afterMethods = getMethodsAnnotatedWith(allClassMethods, After.class);

        var testResults = invokeTests(testClassInstance, beforeMethods, testMethods, afterMethods);
        printTestStatistic(testResults);
    }

    private static TestResults invokeTests(
            Object testClassInstance, Method[] beforeMethods, Method[] testMethods, Method[] afterMethods)
            throws InvocationTargetException, IllegalAccessException {
        int failedTests = 0;

        for (Method testMethod : testMethods) {
            try {
                invoke(testClassInstance, beforeMethods);
                invoke(testClassInstance, testMethod);
            } catch (Throwable ex) {
                log.warn("Test failed: %s".formatted(testMethod.getName()), ex);
                failedTests++;
            } finally {
                invoke(testClassInstance, afterMethods);
            }
        }

        var passedTests = testMethods.length - failedTests;
        return new TestResults(testClassInstance.getClass().getSimpleName(), passedTests, failedTests);
    }

    private static void invoke(Object object, Method... methods)
            throws InvocationTargetException, IllegalAccessException {
        for (Method method : methods) {
            var savedAccessibleFlag = method.canAccess(object);
            if (method.trySetAccessible()) {
                method.invoke(object);
            } else {
                throw new IllegalAccessException("Cannot set access for method %s".formatted(method.getName()));
            }
            method.setAccessible(savedAccessibleFlag);
        }
    }

    private static Method[] getMethodsAnnotatedWith(Method[] allMethods, final Class<? extends Annotation> annotation) {
        var annotatedMethodsCount = 0;
        var annotatedMethods = new Method[allMethods.length];
        for (Method method : allMethods) {
            if (method.isAnnotationPresent(annotation)) {
                annotatedMethods[annotatedMethodsCount] = method;
                annotatedMethodsCount++;
            }
        }
        return Arrays.copyOfRange(annotatedMethods, 0, annotatedMethodsCount);
    }

    public static void printTestStatistic(TestResults testResults) {
        log.info(testResults.testClassName + " tests passed: " + testResults.passed);
        log.info(testResults.testClassName + " tests failed: " + testResults.failed);
        log.info(testResults.testClassName + " tests total: " + (testResults.passed + testResults.failed));
    }
}
