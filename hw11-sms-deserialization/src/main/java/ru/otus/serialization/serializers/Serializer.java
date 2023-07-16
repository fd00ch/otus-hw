package ru.otus.serialization.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.File;
import java.io.IOException;

public interface Serializer {
    String serialize(Object obj) throws JsonProcessingException;
    void serialize(File file, Object obj) throws IOException;
    <T> T deserialize(String serialized, Class<T> clazz) throws JsonProcessingException;
    <T> T deserialize(File file, Class<T> clazz) throws IOException;
}
