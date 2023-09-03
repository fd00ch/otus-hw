package ru.otus.serialization.serializestrategies;

import ru.otus.serialization.model.output.SmsOutput;
import ru.otus.serialization.serializers.YamlSmsSerializer;

public class SmsSerializeYaml extends SmsSerializeStrategy {
        public SmsSerializeYaml(SmsOutput smsOutput) {
                super(smsOutput, new YamlSmsSerializer());
        }
}
