package org.akimov.bot.service.processors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.akimov.bot.intergation.AsicServiceFeignClient;
import org.akimov.bot.model.dto.telegram.TelegramMessage;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
@Service("messageProcessorHashRate")
public class MessageProcessorHashRate implements MessageProcessor {
    private final AsicServiceFeignClient asicServiceFeignClient;

    @Override
    public void process(Long chatId, String msgText, Consumer<TelegramMessage> telegramMessageConsumer) {
        log.info("msgText:{}", msgText);
        var asicSummary = asicServiceFeignClient.getAsicSummary();
        var hashRate = asicSummary.getMiner().getAverageHashrate();
        telegramMessageConsumer.accept(new TelegramMessage(chatId, "Hash rate: %.2f Th/s".formatted(hashRate)));
    }
}
