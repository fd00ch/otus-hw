package ru.otus.serialization.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonSerializer implements Serializer {
    private static final String JSON_EXTENSION = ".json";
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Override
    public String serialize(Object obj) throws JsonProcessingException {
//        return jsonMapper.writeValueAsString(obj);
        return jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj); // pretty print
    }

    @Override
    public void serialize(File file, Object obj) throws IOException {
        jsonMapper.writeValue(file, obj);
    }

    @Override
    public <T> T deserialize(String serialized, Class<T> clazz) throws JsonProcessingException {
        return jsonMapper.readValue(serialized, clazz);
    }

    @Override
    public <T> T deserialize(File file, Class<T> clazz) throws IOException {
        return jsonMapper.readValue(file, clazz);
    }
}
