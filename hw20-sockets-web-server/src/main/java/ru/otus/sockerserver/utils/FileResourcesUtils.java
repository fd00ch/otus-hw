package ru.otus.sockerserver.utils;

import java.io.InputStream;

public class FileResourcesUtils {
    public static InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = FileResourcesUtils.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }
}
