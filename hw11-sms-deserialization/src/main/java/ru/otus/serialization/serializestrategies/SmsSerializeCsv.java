package ru.otus.serialization.serializestrategies;

import ru.otus.serialization.model.output.SmsOutput;
import ru.otus.serialization.serializers.CsvSmsSerializer;

public class SmsSerializeCsv extends SmsSerializeStrategy {
    public SmsSerializeCsv(SmsOutput smsOutput) {
        super(smsOutput, new CsvSmsSerializer());
    }
}
