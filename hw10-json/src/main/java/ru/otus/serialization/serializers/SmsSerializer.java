package ru.otus.serialization.serializers;

import ru.otus.serialization.model.output.SmsOutput;

import java.io.File;
import java.io.IOException;

public interface SmsSerializer {
    void serialize(File file, SmsOutput obj) throws IOException;
    SmsOutput deserialize(File file) throws IOException;
    String getExtension();
}
