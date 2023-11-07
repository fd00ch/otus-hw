package org.akimov.asic.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.akimov.asic.logging.TrackExecutionTime;
import org.akimov.asic.model.status.Status;
import org.akimov.asic.model.summary.Summary;
import org.akimov.asic.service.AsicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/asic")
public class AsicController {
    private final AsicService asicService;

    @TrackExecutionTime
    @GetMapping("/status")
    @Operation(summary = "Get asic status")
    public Status getAsicStatus() {
        var status = asicService.getStatus();
        return status;
    }

    @TrackExecutionTime
    @GetMapping("/summary")
    @Operation(summary = "Get asic summary")
    public Summary getAsicSummary() {
        var summary = asicService.getSummary();
        return summary;
    }

    @TrackExecutionTime
    @PostMapping("/reboot")
    @Operation(summary = "Reboot asic")
    public void postReboot() {
        asicService.reboot();
    }
}
