package ru.otus.serialization.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.otus.serialization.model.output.SmsOutput;

import java.io.File;
import java.io.IOException;

public class XmlSmsSerializer implements SmsSerializer {
    private static final String JSON_EXTENSION = ".xml";
    private final XmlMapper xmlMapper = new XmlMapper();

    @Override
    public String serialize(SmsOutput obj) throws JsonProcessingException {
        return xmlMapper.writeValueAsString(obj);
    }

    @Override
    public void serialize(File file, SmsOutput obj) throws IOException {
        xmlMapper.writeValue(file, obj);
    }

    @Override
    public SmsOutput deserialize(String serialized) throws JsonProcessingException {
        return xmlMapper.readValue(serialized, SmsOutput.class);
    }

    @Override
    public SmsOutput deserialize(File file) throws IOException {
        return xmlMapper.readValue(file, SmsOutput.class);
    }
}
