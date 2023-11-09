package org.akimov.service.processors;

import lombok.extern.slf4j.Slf4j;
import org.akimov.model.dto.telegram.TelegramMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;


@Slf4j
@Primary
@Service("messageProcessorGeneral")
public class MessageProcessorGeneral implements MessageProcessor {

    private final ApplicationContext applicationContext;
    private final MessageProcessor messageProcessorNotSupported;

    public MessageProcessorGeneral(ApplicationContext applicationContext,
                                   @Qualifier("messageProcessorNotSupported") MessageProcessor messageProcessorNotSupported) {
        this.applicationContext = applicationContext;
        this.messageProcessorNotSupported = messageProcessorNotSupported;
    }

    @Override
    public void process(Long chatId, String msgText, Consumer<TelegramMessage> telegramMessageConsumer) {
        for(var cmd : CmdRegistry.values()) {
            if (cmd.getCmd().equals(msgText)) {
                var handler = applicationContext.getBean(cmd.getHandlerName(), MessageProcessor.class);
                handler.process(chatId, msgText, telegramMessageConsumer);
                return;
            }
        }
        messageProcessorNotSupported.process(chatId, msgText, telegramMessageConsumer);
    }
}
