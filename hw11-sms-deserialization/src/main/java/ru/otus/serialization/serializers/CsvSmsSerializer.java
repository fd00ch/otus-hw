package ru.otus.serialization.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import ru.otus.serialization.model.converters.csv.CSV2SmsConverter;
import ru.otus.serialization.model.converters.csv.Sms2CSVConverter;
import ru.otus.serialization.model.output.SmsOutput;
import ru.otus.serialization.model.output.csvoutput.BelongNumberMessagesCSVOutput;

import java.io.File;
import java.io.IOException;

public class CsvSmsSerializer implements SmsSerializer {
    private static final String CSV_EXTENSION = ".csv";
    private final CsvMapper csvMapper = new CsvMapper();
    private final Sms2CSVConverter sms2CSVConverter = new Sms2CSVConverter();
    private final CSV2SmsConverter csv2SmsConverter = new CSV2SmsConverter();

    @Override
    public String serialize(SmsOutput smsOutput) throws JsonProcessingException {
        var csvObject = sms2CSVConverter.apply(smsOutput);
        BelongNumberMessagesCSVOutput[] msgs = csvObject.toArray(new BelongNumberMessagesCSVOutput[0]);
        CsvSchema csvSchema = csvMapper
                .schemaFor(BelongNumberMessagesCSVOutput.class)
                .withHeader();
        return csvMapper.writerFor(BelongNumberMessagesCSVOutput[].class)
                .with(csvSchema)
                .writeValueAsString(msgs);
    }


    @Override
    public void serialize(File file, SmsOutput smsOutput) throws IOException {
        var csvObject = sms2CSVConverter.apply(smsOutput);
        BelongNumberMessagesCSVOutput[] msgs = csvObject.toArray(new BelongNumberMessagesCSVOutput[0]);
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper
                .schemaFor(BelongNumberMessagesCSVOutput.class)
                .withHeader();
        csvMapper.writerFor(BelongNumberMessagesCSVOutput[].class)
                .with(csvSchema)
                .writeValue(file, msgs);
    }

    @Override
    public SmsOutput deserialize(String serialized) {
        return null;
    }

    @Override
    public SmsOutput deserialize(File file) throws IOException {
        CsvSchema objectLineSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        try (MappingIterator<BelongNumberMessagesCSVOutput> objectLines = csvMapper.readerFor(BelongNumberMessagesCSVOutput.class)
                .with(objectLineSchema)
                .readValues(file)) {
            var objectList = objectLines.readAll();
            var deserialized = csv2SmsConverter.apply(objectList);
            return deserialized;
        }
    }
}
