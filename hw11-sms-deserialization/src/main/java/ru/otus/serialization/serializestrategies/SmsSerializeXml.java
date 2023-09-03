package ru.otus.serialization.serializestrategies;

import ru.otus.serialization.model.output.SmsOutput;
import ru.otus.serialization.serializers.XmlSmsSerializer;

public class SmsSerializeXml extends SmsSerializeStrategy {
        public SmsSerializeXml(SmsOutput smsOutput) {
                super(smsOutput, new XmlSmsSerializer());
        }
}
