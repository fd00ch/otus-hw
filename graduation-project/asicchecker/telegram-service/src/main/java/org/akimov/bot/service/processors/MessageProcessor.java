package org.akimov.bot.service.processors;

import org.akimov.bot.model.dto.telegram.TelegramMessage;

import java.util.function.Consumer;

public interface MessageProcessor {
    void process(Long chatId, String msgText, Consumer<TelegramMessage> telegramMessageConsumer);
}
