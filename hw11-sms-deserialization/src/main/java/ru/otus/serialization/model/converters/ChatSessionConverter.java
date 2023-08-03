package ru.otus.serialization.model.converters;

import ru.otus.serialization.model.input.ChatSession;
import ru.otus.serialization.model.input.Message;
import ru.otus.serialization.model.output.ChatSessionOutput;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChatSessionConverter implements Function<ChatSession, ChatSessionOutput> {
    private final MemberConverter memberConverter = new MemberConverter();
    private final MessageConverter messageConverter = new MessageConverter();

    @Override
    public ChatSessionOutput apply(ChatSession chatSession) {
        var members = chatSession.members().stream()
                .map(memberConverter)
                .toList();
        var belongNumberMessages =
                messageConverter.apply(chatSession.messages().stream()
                        .collect(Collectors.groupingBy(
                                Message::belongNumber,
                                Collectors.mapping(Function.identity(),
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        messages -> messages.stream()
                                                .sorted(Comparator.comparing(Message::sendDate))
                                                .collect(Collectors.toList()))))));
        return new ChatSessionOutput(chatSession.chatIdentifier(), members, belongNumberMessages);
    }
}
