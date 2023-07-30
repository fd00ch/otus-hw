package ru.otus.serialization.serializestrategies;

import ru.otus.serialization.model.output.SmsOutput;
import ru.otus.serialization.serializers.JsonSmsSerializer;

public class SmsSerializeJson extends SmsSerializeStrategy {
    public SmsSerializeJson(SmsOutput smsOutput) {
        super(smsOutput, new JsonSmsSerializer());
    }
}
