package org.akimov.intergation;

import org.akimov.model.status.Status;
import org.akimov.model.summary.Summary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "asic-service-client", url = "http://192.168.1.254:8071")
public interface AsicServiceFeignClient {
    @GetMapping("/api/v1/asic/status")
    Status getAsicStatus();

    @GetMapping("/api/v1/asic/summary")
    Summary getAsicSummary();

    @PostMapping("/api/v1/asic/reboot")
    Summary reboot();
}
