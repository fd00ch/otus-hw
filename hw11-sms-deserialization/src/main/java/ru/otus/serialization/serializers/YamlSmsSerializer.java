package ru.otus.serialization.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import ru.otus.serialization.model.output.SmsOutput;

import java.io.File;
import java.io.IOException;

public class YamlSmsSerializer implements SmsSerializer {
    private static final String JSON_EXTENSION = ".yml";
    private final ObjectMapper ymlMapper = new ObjectMapper(new YAMLFactory());

    @Override
    public String serialize(SmsOutput obj) throws JsonProcessingException {
        return ymlMapper.writeValueAsString(obj);
    }

    @Override
    public void serialize(File file, SmsOutput obj) throws IOException {
        ymlMapper.writeValue(file, obj);
    }

    @Override
    public SmsOutput deserialize(String serialized) throws JsonProcessingException {
        return ymlMapper.readValue(serialized, SmsOutput.class);
    }

    @Override
    public SmsOutput deserialize(File file) throws IOException {
        return ymlMapper.readValue(file, SmsOutput.class);
    }
}
