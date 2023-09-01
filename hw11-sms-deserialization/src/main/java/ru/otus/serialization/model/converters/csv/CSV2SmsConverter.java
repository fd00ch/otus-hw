package ru.otus.serialization.model.converters.csv;

import ru.otus.serialization.model.output.*;
import ru.otus.serialization.model.output.csvoutput.BelongNumberMessagesCSVOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;

public class CSV2SmsConverter implements Function<List<BelongNumberMessagesCSVOutput>, SmsOutput> {


    @Override
    public SmsOutput apply(List<BelongNumberMessagesCSVOutput> belongNumberMessagesCSVOutputs) {
        var chatSessions = new ArrayList<ChatSessionOutput>();
        Map<String, List<BelongNumberMessagesCSVOutput>> messagesPerChatIdentifier = belongNumberMessagesCSVOutputs.stream()
                .collect(groupingBy(BelongNumberMessagesCSVOutput::chatIdentifier));

        for (var entry : messagesPerChatIdentifier.entrySet()) {
            var chatIdentifier = entry.getKey();
            var groupedBelongNumberMessagesCSV = entry.getValue();

            var members = new ArrayList<MemberOutput>();

            if (groupedBelongNumberMessagesCSV.size() > 0) {
                var last = groupedBelongNumberMessagesCSV.get(0).last();
                var strings = last.split("\\|");
                for (String las : strings) {
                    var member = new MemberOutput(las);
                    members.add(member);
                }
            }

            var messages = new ArrayList<BelongNumberMessagesOutput>();

            Map<String, List<BelongNumberMessagesCSVOutput>> messagesPerBelongNumber = groupedBelongNumberMessagesCSV
                    .stream()
                    .collect(groupingBy(BelongNumberMessagesCSVOutput::belongNumber));

            for (var entry2: messagesPerBelongNumber.entrySet()) {
                var belongNumber = entry2.getKey();
                var groupedBelongNumberMessagesCSV2 = entry2.getValue();
                var messageContents = groupedBelongNumberMessagesCSV2.stream()
                        .map(s -> new MessageContentOutput(s.sendDate(), s.text()))
                        .toList();

                var belongNumberMessages = new BelongNumberMessagesOutput(belongNumber, messageContents);
                messages.add(belongNumberMessages);
            }

            var chatSession = new ChatSessionOutput(chatIdentifier, members, messages);
            chatSessions.add(chatSession);
        }
        return new SmsOutput(chatSessions);
    }
}
