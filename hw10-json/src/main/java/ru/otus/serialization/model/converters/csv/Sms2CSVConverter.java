package ru.otus.serialization.model.converters.csv;

import ru.otus.serialization.model.output.MemberOutput;
import ru.otus.serialization.model.output.SmsOutput;
import ru.otus.serialization.model.output.csvoutput.BelongNumberMessagesCSVOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Sms2CSVConverter implements Function<SmsOutput, List<BelongNumberMessagesCSVOutput>> {
    @Override
    public List<BelongNumberMessagesCSVOutput> apply(SmsOutput smsOutput) {
        List<BelongNumberMessagesCSVOutput> csvMessages = new ArrayList<>();
        var chatSessions = smsOutput.chatSessions();
        for (var chatSession : chatSessions) {
            var lasts = chatSession.members().stream()
                    .map(MemberOutput::last)
                    .collect(Collectors.joining("|"));
            for (var message : chatSession.messages()) {
                for (var messageContent : message.messageContents()) {
                    var belongNumberMessagesCSVOutput =
                            new BelongNumberMessagesCSVOutput(chatSession.chatIdentifier(), lasts,
                                    message.belongNumber(), messageContent.sendDate(), messageContent.text());
                    csvMessages.add(belongNumberMessagesCSVOutput);
                }
            }
        }
        return csvMessages;
    }
}