package org.akimov.service.processors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.akimov.intergation.AsicServiceFeignClient;
import org.akimov.model.telegram.TelegramMessage;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service("messageProcessorPcbPwr")
public class MessageProcessorPcbPwr implements MessageProcessor {
    private final AsicServiceFeignClient asicServiceFeignClient;

    @Override
    public void process(Long chatId, String msgText, Consumer<TelegramMessage> telegramMessageConsumer) {
        log.info("msgText:{}", msgText);
        var asicSummary = asicServiceFeignClient.getAsicSummary();
        var chains = asicSummary.getMiner().getChains();
        var chainsPowerUsage = chains.stream()
                .map(chain -> "Pcb%d power usage: %.2f kW".formatted(chain.getId(), chain.getPowerUsage()/1000f))
                .collect(Collectors.joining("\n"));
        telegramMessageConsumer.accept(new TelegramMessage(chatId, chainsPowerUsage));
    }
}
