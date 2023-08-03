package ru.otus.serialization.serializestrategies;

import ru.otus.serialization.model.output.SmsOutput;
import ru.otus.serialization.serializers.SmsSerializer;

import java.io.File;
import java.io.IOException;

public abstract class SmsSerializeStrategy {

        protected SmsSerializer smsSerializer;
        protected SmsOutput smsOutput;

        public SmsSerializeStrategy(SmsOutput smsOutput, SmsSerializer smsSerializer) {
                this.smsSerializer = smsSerializer;
                this.smsOutput = smsOutput;
        }

        public void serialize(File file) throws IOException {
                smsSerializer.serialize(file, smsOutput);
        }

        public SmsOutput deserialize(File file) throws IOException {
                return smsSerializer.deserialize(file);
        }

        public SmsSerializer getSmsSerializer() {
                return smsSerializer;
        }
}
