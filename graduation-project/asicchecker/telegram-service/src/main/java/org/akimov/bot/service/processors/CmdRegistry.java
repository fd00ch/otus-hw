package org.akimov.bot.service.processors;

import lombok.Getter;

@Getter
public enum CmdRegistry {
    // get info
    HR("/hr", "messageProcessorHashRate"),
    TEMP("/temp", "messageProcessorTemp"),
    PWR("/pwr", "messageProcessorPower"),
    FANS("/fans", "messageProcessorFans"),
    PCB_HR("/pcbhr", "messageProcessorPcbHr"),
    PCB_TEMP("/pcbtemp", "messageProcessorPcbTemp"),
    PCB_PWR("/pcbpwr", "messageProcessorPcbPwr"),
    PCB_VOLT("/pcbvolt", "messageProcessorPcbVolt"),

    // control
    REBOOT("/reboot", "messageProcessorReboot");

    private final String cmd;
    private final String handlerName;

    CmdRegistry(String cmd, String handlerName) {
        this.cmd = cmd;
        this.handlerName = handlerName;
    }

}
