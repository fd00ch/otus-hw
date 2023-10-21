package org.akimov.asic.service;

import lombok.RequiredArgsConstructor;
import org.akimov.asic.integrations.AsicIntegration;
import org.akimov.asic.model.Status;
import org.akimov.asic.model.Summary;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AsicServiceImpl implements AsicService {
    private final AsicIntegration asicIntegration;
    @Override
    public Status getStatus() {
        return asicIntegration.getStatus();
    }

    @Override
    public Summary getSummary() {
        return asicIntegration.getSummary();
    }
}
