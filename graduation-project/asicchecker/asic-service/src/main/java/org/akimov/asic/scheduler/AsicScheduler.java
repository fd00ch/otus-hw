package org.akimov.asic.scheduler;

//import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.akimov.asic.service.AsicService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
//@RequiredArgsConstructor
public class AsicScheduler {
     private final AsicService asicService;
    private final AtomicInteger averageHashrate;
    private final AtomicInteger maxChipTemp;

    public AsicScheduler(AsicService asicService, MeterRegistry meterRegistry) {
        this.asicService = asicService;
        averageHashrate = new AtomicInteger();
        maxChipTemp = new AtomicInteger();
        meterRegistry.gauge("averageHashrate", averageHashrate);
        meterRegistry.gauge("maxChipTemp", maxChipTemp);
    }

    @Async
    @Scheduled(fixedRate = 10_000)
    public void updateAverageHashrate() {
        var summary = asicService.getSummary();
        averageHashrate.set((int) summary.getMiner().getAverageHashrate());
    }

    @Async
    @Scheduled(fixedRate = 10_000)
    public void updateMaxChipTemp() {
        var summary = asicService.getSummary();
        maxChipTemp.set(summary.getMiner().getChipTemp().getMax());
    }
}
