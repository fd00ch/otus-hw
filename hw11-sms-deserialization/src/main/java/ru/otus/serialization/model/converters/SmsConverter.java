package ru.otus.serialization.model.converters;

import ru.otus.serialization.model.input.SmsInput;
import ru.otus.serialization.model.output.SmsOutput;

import java.util.function.Function;

public class SmsConverter implements Function<SmsInput, SmsOutput> {

    private final ChatSessionConverter chatSessionConverter = new ChatSessionConverter();
    @Override
    public SmsOutput apply(SmsInput smsInput) {
        return new SmsOutput(smsInput.chatSessions().stream()
                .map(chatSessionConverter)
                .toList());
    }
}
