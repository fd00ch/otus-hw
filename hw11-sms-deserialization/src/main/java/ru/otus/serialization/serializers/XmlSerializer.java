package ru.otus.serialization.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class XmlSerializer implements Serializer {
    private static final String JSON_EXTENSION = ".xml";
    private final XmlMapper xmlMapper = new XmlMapper();

    @Override
    public String serialize(Object obj) throws JsonProcessingException {
        return xmlMapper.writeValueAsString(obj);
    }

    @Override
    public void serialize(File file, Object obj) throws IOException {
        xmlMapper.writeValue(file, obj);
    }

    @Override
    public <T> T deserialize(String serialized, Class<T> clazz) throws JsonProcessingException {
        return xmlMapper.readValue(serialized, clazz);
    }

    @Override
    public <T> T deserialize(File file, Class<T> clazz) throws IOException {
        return xmlMapper.readValue(file, clazz);
    }
}
