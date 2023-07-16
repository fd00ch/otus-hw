package ru.otus.serialization.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class YamlSerializer implements Serializer {
    private static final String JSON_EXTENSION = ".yml";
    private final ObjectMapper ymlMapper = new ObjectMapper(new YAMLFactory());

    @Override
    public String serialize(Object obj) throws JsonProcessingException {
        return ymlMapper.writeValueAsString(obj);
    }

    @Override
    public void serialize(File file, Object obj) throws IOException {
        ymlMapper.writeValue(file, obj);
    }

    @Override
    public <T> T deserialize(String serialized, Class<T> clazz) throws JsonProcessingException {
        return ymlMapper.readValue(serialized, clazz);
    }

    @Override
    public <T> T deserialize(File file, Class<T> clazz) throws IOException {
        return ymlMapper.readValue(file, clazz);
    }
}
