package org.akimov.asic.controller;

import lombok.RequiredArgsConstructor;
import org.akimov.asic.model.Status;
import org.akimov.asic.model.Summary;
import org.akimov.asic.service.AsicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AsicController {
    private final AsicService asicService;

    @GetMapping("/asic/status")
    public Status getAsicStatus() {
        var status = asicService.getStatus();
        return status;
    }

    @GetMapping("/asic/summary")
    public Summary getAsicSummary() {
        var summary = asicService.getSummary();
        return summary;
    }
}
