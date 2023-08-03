package ru.otus.serialization.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import ru.otus.serialization.model.output.SmsOutput;

import java.io.File;
import java.io.IOException;

public class YamlSmsSerializer implements SmsSerializer {
    private static final String YML_EXTENSION = "yml";
    private final ObjectMapper ymlMapper = new ObjectMapper(new YAMLFactory());

    @Override
    public void serialize(File file, SmsOutput obj) throws IOException {
        ymlMapper.writeValue(file, obj);
    }

    @Override
    public SmsOutput deserialize(File file) throws IOException {
        return ymlMapper.readValue(file, SmsOutput.class);
    }

    @Override
    public String getExtension() {
        return YML_EXTENSION;
    }
}
