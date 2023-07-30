package ru.otus.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.serialization.model.converters.SmsConverter;
import ru.otus.serialization.model.input.SmsInput;
import ru.otus.serialization.serializestrategies.SmsSerializeCsv;
import ru.otus.serialization.serializestrategies.SmsSerializeJson;
import ru.otus.serialization.serializestrategies.SmsSerializeXml;
import ru.otus.serialization.serializestrategies.SmsSerializeYaml;

import java.io.*;

public class SerializationOtus {
    private static final Logger LOGGER = LoggerFactory.getLogger(SerializationOtus.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final SmsConverter smsConverter = new SmsConverter();

    public static void main(String[] args) throws IOException {
        String inputFileName = "json/sms-256866-480df9.json";
        SmsInput smsInput = objectMapper.readValue(new SerializationOtus().getFileFromResourceAsStream(inputFileName), SmsInput.class);

        var smsOutput = smsConverter.apply(smsInput);

        var json = new SmsSerializeJson(smsOutput);
        var jsonFile = new File("serializedFiles/smsOutput.json");
        json.serialize(jsonFile);
        var deserializedJson = json.deserialize(jsonFile);
        LOGGER.info(String.format("deserialized json: %s", deserializedJson));

        var yaml = new SmsSerializeYaml(smsOutput);
        var yamlFile = new File("serializedFiles/smsOutput.yml");
        yaml.serialize(yamlFile);
        var deserializedYaml = yaml.deserialize(yamlFile);
        LOGGER.info(String.format("deserialized yaml: %s", deserializedYaml));

        var xml = new SmsSerializeXml(smsOutput);
        var xmlFile = new File("serializedFiles/smsOutput.xml");
        xml.serialize(xmlFile);
        var deserializedXml = xml.deserialize(xmlFile);
        LOGGER.info(String.format("deserialized xml: %s", deserializedXml));

        var csv = new SmsSerializeCsv(smsOutput);
        var csvFile = new File("serializedFiles/smsOutput.csv");
        csv.serialize(csvFile);
        var deserializedCsv = csv.deserialize(csvFile);
        LOGGER.info(String.format("deserialized csv: %s", deserializedCsv));
    }

    private InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }
}
