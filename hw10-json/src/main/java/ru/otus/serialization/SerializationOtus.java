package ru.otus.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.serialization.model.converters.SmsConverter;
import ru.otus.serialization.model.input.SmsInput;
import ru.otus.serialization.serializestrategies.*;

import java.io.*;

public class SerializationOtus {
    private static final Logger LOGGER = LoggerFactory.getLogger(SerializationOtus.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final SmsConverter smsConverter = new SmsConverter();

    public static void main(String[] args) throws IOException {
        SmsInput smsInput = deserializeFromResource();

        var smsOutput = smsConverter.apply(smsInput);

        serializeAndDeserialize(new SmsSerializeJson(smsOutput));
        serializeAndDeserialize(new SmsSerializeYaml(smsOutput));
        serializeAndDeserialize(new SmsSerializeXml(smsOutput));
        serializeAndDeserialize(new SmsSerializeCsv(smsOutput));
    }

    private static SmsInput deserializeFromResource() throws IOException {
        String inputFileName = "/json/sms-256866-480df9.json";
        var inputFileStream = SerializationOtus.class.getResourceAsStream(inputFileName);
        SmsInput smsInput = objectMapper.readValue(inputFileStream, SmsInput.class);
        return smsInput;
    }

    private static void serializeAndDeserialize(SmsSerializeStrategy strategy) throws IOException {
        var extension = strategy.getSmsSerializer().getExtension();
        var outputFile = new File("serializedFiles/smsOutput." + extension);
        strategy.serialize(outputFile);
        var deserializedJson = strategy.deserialize(outputFile);
        LOGGER.info(String.format("deserialized %s: %s", extension, deserializedJson));
    }
}
