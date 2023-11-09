package org.akimov.bot.service.processors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.akimov.bot.intergation.AsicServiceFeignClient;
import org.akimov.bot.model.dto.telegram.TelegramMessage;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
@Service("messageProcessorTemp")
public class MessageProcessorTemp implements MessageProcessor {
    private final AsicServiceFeignClient asicServiceFeignClient;

    @Override
    public void process(Long chatId, String msgText, Consumer<TelegramMessage> telegramMessageConsumer) {
        log.info("msgText:{}", msgText);
        var asicSummary = asicServiceFeignClient.getAsicSummary();
        var maxChipTemp = asicSummary.getMiner().getChipTemp().getMax();
        telegramMessageConsumer.accept(new TelegramMessage(chatId, "Max chip temp: %d°".formatted(maxChipTemp)));
    }
}
