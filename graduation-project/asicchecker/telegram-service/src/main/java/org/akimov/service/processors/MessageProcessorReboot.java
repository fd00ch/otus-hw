package org.akimov.service.processors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.akimov.intergation.AsicServiceFeignClient;
import org.akimov.model.dto.telegram.TelegramMessage;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
@Service("messageProcessorReboot")
public class MessageProcessorReboot implements MessageProcessor {
    private final AsicServiceFeignClient asicServiceFeignClient;

    @Override
    public void process(Long chatId, String msgText, Consumer<TelegramMessage> telegramMessageConsumer) {
        log.info("msgText:{}", msgText);
        asicServiceFeignClient.reboot();
        telegramMessageConsumer.accept(new TelegramMessage(chatId, "Reboot command sent"));
    }
}
