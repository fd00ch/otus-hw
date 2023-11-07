package org.akimov.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.telegram")
@Getter
@Setter
public class TelegramClientConfig {
    String telegramBotToken;
    Long allowedChatId;
}
