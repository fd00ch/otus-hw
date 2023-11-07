package org.akimov.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.akimov.config.TelegramClientConfig;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Consumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccessChecker {
    private final TelegramClientConfig telegramClientConfig;
    public boolean checkAccess(Long chatId) {
        return Objects.equals(chatId, telegramClientConfig.getAllowedChatId());
    }

    public void checkAccess(Long chatId, Consumer<Long> sendAccessDenied) {
        var accessGranted = Objects.equals(chatId, telegramClientConfig.getAllowedChatId());
        if (!accessGranted) {
            log.info("Access denied for chatId: {}", chatId);
            sendAccessDenied.accept(chatId);
        }
    }
}
