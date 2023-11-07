package org.akimov.asic.integrations;

import lombok.RequiredArgsConstructor;
import org.akimov.asic.model.token.AdminTokenRequest;
import org.akimov.asic.model.token.AdminTokenResponse;
import org.akimov.asic.model.status.Status;
import org.akimov.asic.model.summary.Summary;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class AsicIntegration {
    private final WebClient asicServiceWebClient;
    private final RestTemplate restTemplate;

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

    public void reboot() {
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
//                .subscribe(responseEntity -> {
//                    // Handle success response here
//                    HttpStatusCode status = responseEntity.getStatusCode();
//                    URI location = responseEntity.getHeaders().getLocation();
//                    AdminTokenResponse adminTokenResponse = responseEntity.getBody();    // Response body
//                    // handle response as necessary
//                    },
//                        error -> {
//                            // Handle the error here
//                            if (error instanceof WebClientResponseException webClientResponseException) {
//                                HttpStatusCode status = webClientResponseException.getStatusCode();
//                                System.out.println("Error Status Code: " + status.value());
//                                //...
//                            } else {
//                                // Handle other types of errors
//                                System.err.println("An unexpected error occurred: " + error.getMessage());
//                            }
//                });
        return answer.getToken();
    }

    public AdminTokenResponse getAdminTokenSync() {
        var adminTokenRequest = new AdminTokenRequest("admin");
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var entity = new HttpEntity<>(adminTokenRequest, headers);
        URI targetUrl = UriComponentsBuilder.fromUriString("http://192.168.1.110/")
                .path("/api/v1/unlock")
                .build()
                .encode()
                .toUri();
        try {
            var token = restTemplate.exchange(targetUrl, HttpMethod.POST, entity, AdminTokenResponse.class);
            return token.getBody();
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
            return null;
        }
    }
}
