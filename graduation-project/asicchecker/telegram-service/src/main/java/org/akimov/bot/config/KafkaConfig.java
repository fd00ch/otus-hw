package org.akimov.bot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.akimov.bot.exception.BotException;
import org.akimov.bot.service.TelegramBot;
import org.akimov.bot.model.dto.telegram.TelegramMessage;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor
@Slf4j
@EnableKafka
public class KafkaConfig {

    public static final String SEND_TELEGRAM_MSG = "send-telegram-msg";
    public static final String GROUP_ID = "telegram-service";
    private final TelegramBot telegramBot;
    private final ObjectMapper objectMapper;

    @Bean
    public NewTopic sendTelegramMessageTopic() {
        return TopicBuilder
                .name(SEND_TELEGRAM_MSG)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @KafkaListener(groupId = GROUP_ID, topics = SEND_TELEGRAM_MSG)
    public void asicServiceResponseListen(String msgAsString) {
        TelegramMessage telegramMessage;
        try {
            telegramMessage = objectMapper.readValue(msgAsString, TelegramMessage.class);
        } catch (Exception ex) {
            log.error("can't parse message:{}", msgAsString, ex);
            throw new BotException("can't parse message:" + msgAsString, ex);
        }
        telegramBot.sendTelegramMsg(telegramMessage.getChatId(), telegramMessage.getMessage());
    }
}
