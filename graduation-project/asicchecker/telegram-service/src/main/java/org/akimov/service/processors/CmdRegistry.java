package org.akimov.service.processors;

import lombok.Getter;

@Getter
public enum CmdRegistry {
    HR("/hr", "messageProcessorHashRate"),
    TEMP("/temp", "messageProcessorTemp"),
    PWR("/pwr", "messageProcessorPower"),
    FANS("/fans", "messageProcessorFans"),
    PCB_HR("/pcbhr", "messageProcessorPcbHr"),
    PCB_TEMP("/pcbtemp", "messageProcessorPcbTemp"),
    PCB_PWR("/pcbpwr", "messageProcessorPcbPwr"),
    PCB_VOLT("/pcbvolt", "messageProcessorPcbVolt");

    private final String cmd;
    private final String handlerName;

    CmdRegistry(String cmd, String handlerName) {
        this.cmd = cmd;
        this.handlerName = handlerName;
    }

}
