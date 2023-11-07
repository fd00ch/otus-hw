package org.akimov.intergation;

import org.akimov.model.status.Status;
import org.akimov.model.summary.Summary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "asic-service-client", url = "http://192.168.1.254:8071")
public interface AsicServiceFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/asic/status")
    Status getAsicStatus();

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/asic/summary")
    Summary getAsicSummary();
}
