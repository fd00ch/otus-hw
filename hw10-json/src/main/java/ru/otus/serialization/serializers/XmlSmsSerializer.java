package ru.otus.serialization.serializers;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.otus.serialization.model.output.SmsOutput;

import java.io.File;
import java.io.IOException;

public class XmlSmsSerializer implements SmsSerializer {
    private static final String XML_EXTENSION = "xml";
    private final XmlMapper xmlMapper = new XmlMapper();

    @Override
    public void serialize(File file, SmsOutput obj) throws IOException {
        xmlMapper.writerWithDefaultPrettyPrinter().writeValue(file, obj);
    }

    @Override
    public SmsOutput deserialize(File file) throws IOException {
        return xmlMapper.readValue(file, SmsOutput.class);
    }

    @Override
    public String getExtension() {
        return XML_EXTENSION;
    }
}
