package ru.otus.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.serialization.model.converters.SmsConverter;
import ru.otus.serialization.model.input.SmsInput;
import ru.otus.serialization.model.output.SmsOutput;
import ru.otus.serialization.serializers.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SerializationOtus {
    private static final Logger LOGGER = LoggerFactory.getLogger(SerializationOtus.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final SmsConverter smsConverter = new SmsConverter();

    public static void main(String[] args) throws IOException {
        SerializationOtus serializationOtus = new SerializationOtus();
        String fileName = "json/sms-256866-480df9.json";
        SmsInput smsInput = objectMapper.readValue(serializationOtus.getFileFromResourceAsStream(fileName), SmsInput.class);
        System.out.println(smsInput);

        var smsOutput = smsConverter.apply(smsInput);

        Serializer serializer = new JsonSerializer();
        var jsonFile = new File("smsOutput.json");
        serializer.serialize(jsonFile, smsOutput);
        var deserialized = serializer.deserialize(jsonFile, SmsOutput.class);
//        LOGGER.info(json);

        Serializer serializer2 = new YamlSerializer();
        var yamlFile = new File("smsOutput.yml");
        serializer2.serialize(yamlFile, smsOutput);
        var deserialized2 = serializer2.deserialize(yamlFile, SmsOutput.class);
//        LOGGER.info(yaml);

        Serializer serializer3 = new XmlSerializer();
        var xmlFile = new File("smsOutput.xml");
        serializer3.serialize(xmlFile, smsOutput);
        var deserialized3 = serializer3.deserialize(xmlFile, SmsOutput.class);
//        LOGGER.info(xml);


        var messageOutput = smsOutput.chatSessions().get(0).messages().get(0); // +
        var memberOutput = smsOutput.chatSessions().get(0).members().get(0); // +
        var chatSessionOutput = smsOutput.chatSessions().get(0); // +
        Serializer serializer4 = new CsvSerializer();
        String csv = serializer4.serialize(messageOutput);
        System.out.println(csv);

//        InputStream is = serializationOtus.getFileFromResourceAsStream(fileName);
//        printInputStream(is);


    }

    private InputStream getFileFromResourceAsStream(String fileName) {
        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }

    private static void printInputStream(InputStream is) {

        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
