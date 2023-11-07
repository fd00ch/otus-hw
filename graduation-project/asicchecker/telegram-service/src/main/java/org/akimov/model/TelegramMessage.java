package org.akimov.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TelegramMessage {
    private Long chatId;
    private String message;
}
