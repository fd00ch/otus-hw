package org.akimov.service.processors;

import org.akimov.model.telegram.TelegramMessage;

import java.util.function.Consumer;

public interface MessageProcessor {
    void process(Long chatId, String msgText, Consumer<TelegramMessage> telegramMessageConsumer);
}
