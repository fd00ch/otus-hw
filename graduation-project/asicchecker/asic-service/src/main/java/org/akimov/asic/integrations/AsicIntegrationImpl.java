package org.akimov.asic.integrations;

import lombok.RequiredArgsConstructor;
import org.akimov.asic.model.status.Status;
import org.akimov.asic.model.summary.Summary;
import org.akimov.asic.model.token.AdminTokenRequest;
import org.akimov.asic.model.token.AdminTokenResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class AsicIntegrationImpl implements AsicIntegration {
    private final WebClient asicServiceWebClient;

    @Override
    public Status getStatus() {
        return asicServiceWebClient.get()
                .uri("/api/v1/status")
                .retrieve()
                .bodyToMono(Status.class)
                .block();
    }

    @Override
    public Summary getSummary() {
        return asicServiceWebClient.get()
                .uri("/api/v1/summary")
                .retrieve()
                .bodyToMono(Summary.class)
                .block();
    }

    @Override
    public void reboot() { //TODO security
        var adminToken = getAdminToken();
        asicServiceWebClient.post()
                .uri("/api/v1/system/reboot")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + adminToken)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    private String getAdminToken() {
        var adminTokenRequest = new AdminTokenRequest("admin");
        var answer = asicServiceWebClient.post()
                .uri("/api/v1/unlock")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(adminTokenRequest)
                .retrieve()
                .bodyToMono(AdminTokenResponse.class)
                .block();
        return answer.getToken();
    }
}
