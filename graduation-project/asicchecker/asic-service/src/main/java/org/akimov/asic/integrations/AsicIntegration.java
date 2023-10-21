package org.akimov.asic.integrations;

import lombok.RequiredArgsConstructor;
import org.akimov.asic.model.Status;
import org.akimov.asic.model.Summary;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class AsicIntegration {
    private final WebClient asicServiceWebClient;

    public Status getStatus() {
        return asicServiceWebClient.get()
                .uri("/api/v1/status")
                .retrieve()
//                .onStatus(
//                        httpStatus -> httpStatus.isError(),
//                        clientResponse -> Mono.error(new AppException("PRODUCT_DETAILS_SERVICE_INTEGRATION_ERROR"))
//                )
                .bodyToMono(Status.class)
                .block();
    }

    public Summary getSummary() {
        return asicServiceWebClient.get()
                .uri("/api/v1/summary")
                .retrieve()
//                .onStatus(
//                        httpStatus -> httpStatus.isError(),
//                        clientResponse -> Mono.error(new AppException("PRODUCT_DETAILS_SERVICE_INTEGRATION_ERROR"))
//                )
                .bodyToMono(Summary.class)
                .block();
    }
}
