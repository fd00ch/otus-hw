package ru.otus.serialization.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.otus.serialization.model.output.SmsOutput;

import java.io.File;
import java.io.IOException;

public interface SmsSerializer {
    String serialize(SmsOutput obj) throws JsonProcessingException;
    void serialize(File file, SmsOutput obj) throws IOException;
    SmsOutput deserialize(String serialized) throws JsonProcessingException;
    SmsOutput deserialize(File file) throws IOException;
}
