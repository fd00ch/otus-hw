package org.akimov.bot.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.akimov.bot.model.dto.telegram.TelegramMessage;
import org.akimov.bot.service.processors.MessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.function.Consumer;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final AccessChecker accessChecker;
    private final MessageProcessor messageProcessor;

    @Autowired
    public TelegramBot(Environment env, AccessChecker accessChecker,
                       @Qualifier("messageProcessorGeneral") MessageProcessor messageProcessor) {
        super(env.getProperty("TELEGRAM_BOT_TOKEN"));
        this.accessChecker = accessChecker;
        this.messageProcessor = messageProcessor;
    }

    @PostConstruct
    public void registerBot(){
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        log.info("Receive new Update. updateID: %d, chatId: %d".formatted(update.getUpdateId(), chatId));
        Consumer<Long> sendAccessDenied = (chatIdParam) -> sendTelegramMsg(chatIdParam, "Access denied");
        if (accessChecker.checkAccess(chatId, sendAccessDenied)) {
            Consumer<TelegramMessage> sendTelegramMessage = this::sendTelegramMsg;
            String inputText = update.getMessage().getText();
            messageProcessor.process(chatId, inputText, sendTelegramMessage);
        }
    }

    public void sendTelegramMsg(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendTelegramMsg(TelegramMessage telegramMessage) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(telegramMessage.getChatId());
        sendMessage.setText(telegramMessage.getMessage());
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return "AsicCheckerBot";
    }
}
