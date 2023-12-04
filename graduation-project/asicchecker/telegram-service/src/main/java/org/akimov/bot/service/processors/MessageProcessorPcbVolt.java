package org.akimov.bot.service.processors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.akimov.bot.intergation.AsicServiceFeignClient;
import org.akimov.bot.model.dto.telegram.TelegramMessage;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service("messageProcessorPcbVolt")
public class MessageProcessorPcbVolt implements MessageProcessor {
    private final AsicServiceFeignClient asicServiceFeignClient;

    @Override
    public void process(Long chatId, String msgText, Consumer<TelegramMessage> telegramMessageConsumer) {
        log.info("msgText:{}", msgText);
        var asicSummary = asicServiceFeignClient.getAsicSummary();
        var chains = asicSummary.getMiner().getChains();
        var chainsVoltage = chains.stream()
                .map(chain -> "Pcb%d voltage: %.1f V".formatted(chain.getId(), chain.getVoltage()/1000f))
                .collect(Collectors.joining("\n"));
        telegramMessageConsumer.accept(new TelegramMessage(chatId, chainsVoltage));
    }
}
