package ru.otus.serialization.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.otus.serialization.model.output.SmsOutput;

import java.io.File;
import java.io.IOException;

public class JsonSmsSerializer implements SmsSerializer {
    private static final String JSON_EXTENSION = ".json";
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Override
    public void serialize(File file, SmsOutput obj) throws IOException {
        jsonMapper.writeValue(file, obj);
    }

    @Override
    public SmsOutput deserialize(File file) throws IOException {
        return jsonMapper.readValue(file, SmsOutput.class);
    }
}
