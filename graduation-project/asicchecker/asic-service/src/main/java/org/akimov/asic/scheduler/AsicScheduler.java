package org.akimov.asic.scheduler;

import io.micrometer.core.instrument.MeterRegistry;
import org.akimov.asic.service.AsicService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AsicScheduler {
     private final AsicService asicService;
    private final AtomicInteger averageHashrate;
    private final AtomicInteger maxChipTemp;
    private final AtomicInteger fan1Rpm;
    private final AtomicInteger fan2Rpm;
    private final AtomicInteger fan3Rpm;
    private final AtomicInteger fan4Rpm;
    private final AtomicInteger chain1Hashrate;
    private final AtomicInteger chain2Hashrate;
    private final AtomicInteger chain3Hashrate;
    private final AtomicInteger chain1MaxChipTemp;
    private final AtomicInteger chain2MaxChipTemp;
    private final AtomicInteger chain3MaxChipTemp;
    private final AtomicInteger chain1Voltage;
    private final AtomicInteger chain2Voltage;
    private final AtomicInteger chain3Voltage;

    public AsicScheduler(AsicService asicService, MeterRegistry meterRegistry) {
        this.asicService = asicService;
        averageHashrate = new AtomicInteger();
        maxChipTemp = new AtomicInteger();

        fan1Rpm = new AtomicInteger();
        fan2Rpm = new AtomicInteger();
        fan3Rpm = new AtomicInteger();
        fan4Rpm = new AtomicInteger();

        chain1Hashrate = new AtomicInteger();
        chain2Hashrate = new AtomicInteger();
        chain3Hashrate = new AtomicInteger();

        chain1MaxChipTemp = new AtomicInteger();
        chain2MaxChipTemp = new AtomicInteger();
        chain3MaxChipTemp = new AtomicInteger();

        chain1Voltage = new AtomicInteger();
        chain2Voltage = new AtomicInteger();
        chain3Voltage = new AtomicInteger();

        meterRegistry.gauge("averageHashrate", averageHashrate);
        meterRegistry.gauge("maxChipTemp", maxChipTemp);

        meterRegistry.gauge("fan1Rpm", fan1Rpm);
        meterRegistry.gauge("fan2Rpm", fan2Rpm);
        meterRegistry.gauge("fan3Rpm", fan3Rpm);
        meterRegistry.gauge("fan4Rpm", fan4Rpm);

        meterRegistry.gauge("chain1MaxChipTemp", chain1MaxChipTemp);
        meterRegistry.gauge("chain2MaxChipTemp", chain2MaxChipTemp);
        meterRegistry.gauge("chain3MaxChipTemp", chain3MaxChipTemp);

        meterRegistry.gauge("chain1Hashrate", chain1Hashrate);
        meterRegistry.gauge("chain2Hashrate", chain2Hashrate);
        meterRegistry.gauge("chain3Hashrate", chain3Hashrate);

        meterRegistry.gauge("chain1Voltage", chain1Voltage);
        meterRegistry.gauge("chain2Voltage", chain2Voltage);
        meterRegistry.gauge("chain3Voltage", chain3Voltage);
    }

    @Scheduled(cron = "*/10 * * * * *") // every 10 seconds
    public void updateMainParameters() {
        var summary = asicService.getSummary();
        averageHashrate.set((int) summary.getMiner().getAverageHashrate());
        maxChipTemp.set(summary.getMiner().getChipTemp().getMax());

        fan1Rpm.set(summary.getMiner().getCooling().getFans().get(0).getRpm());
        fan2Rpm.set(summary.getMiner().getCooling().getFans().get(1).getRpm());
        fan3Rpm.set(summary.getMiner().getCooling().getFans().get(2).getRpm());
        fan4Rpm.set(summary.getMiner().getCooling().getFans().get(3).getRpm());

        chain1MaxChipTemp.set(summary.getMiner().getChains().get(0).getChipTemp().getMax());
        chain2MaxChipTemp.set(summary.getMiner().getChains().get(1).getChipTemp().getMax());
        chain3MaxChipTemp.set(summary.getMiner().getChains().get(2).getChipTemp().getMax());

        chain1Hashrate.set((int) summary.getMiner().getChains().get(0).getHashrateRt());
        chain2Hashrate.set((int) summary.getMiner().getChains().get(1).getHashrateRt());
        chain3Hashrate.set((int) summary.getMiner().getChains().get(2).getHashrateRt());

        chain1Voltage.set(summary.getMiner().getChains().get(0).getVoltage());
        chain2Voltage.set(summary.getMiner().getChains().get(1).getVoltage());
        chain3Voltage.set(summary.getMiner().getChains().get(2).getVoltage());
    }
}
