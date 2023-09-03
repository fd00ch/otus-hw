package ru.otus.serialization.model.converters;

import ru.otus.serialization.model.input.Message;
import ru.otus.serialization.model.output.BelongNumberMessagesOutput;
import ru.otus.serialization.model.output.MessageContentOutput;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class MessageConverter implements Function<Map<String, List<Message>>, List<BelongNumberMessagesOutput>> {

    @Override
    public List<BelongNumberMessagesOutput> apply(Map<String, List<Message>> messages) {
        var res = messages.entrySet().stream()
                .map(entry -> {
                    var belongNumber = entry.getKey();
                    var messageContents = entry.getValue().stream()
                            .map(msg -> new MessageContentOutput(msg.sendDate(), msg.text()))
                            .toList();
                    return new BelongNumberMessagesOutput(belongNumber, messageContents);
                })
                .toList();
        return res;
    }
}
