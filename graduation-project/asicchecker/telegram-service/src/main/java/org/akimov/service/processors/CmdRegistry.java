package org.akimov.service.processors;

import lombok.Getter;

@Getter
public enum CmdRegistry {
    HR("/hr", "messageProcessorHashRate"),
    TEMP("/temp", "messageProcessorTemp");

    private final String cmd;
    private final String handlerName;

    CmdRegistry(String cmd, String handlerName) {
        this.cmd = cmd;
        this.handlerName = handlerName;
    }

}
