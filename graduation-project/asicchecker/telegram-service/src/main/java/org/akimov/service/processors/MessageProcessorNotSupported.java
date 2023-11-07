package org.akimov.service.processors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.akimov.model.telegram.TelegramMessage;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Slf4j
@AllArgsConstructor
@Service("messageProcessorNotSupported")
public class MessageProcessorNotSupported implements MessageProcessor {

    @Override
    public void process(Long chatId, String msgText, Consumer<TelegramMessage> telegramMessageConsumer) {
        log.info("msgText:{}", msgText);
        var message = new TelegramMessage(chatId, "Not supported command");
        telegramMessageConsumer.accept(message);

    }
}
