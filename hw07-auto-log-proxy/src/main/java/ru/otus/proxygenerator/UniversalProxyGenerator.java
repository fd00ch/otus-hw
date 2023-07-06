package ru.otus.proxygenerator;

public interface UniversalProxyGenerator {
    <I, T extends I> Object generateProxy(Class<I> interfaze, T clazz);
}
