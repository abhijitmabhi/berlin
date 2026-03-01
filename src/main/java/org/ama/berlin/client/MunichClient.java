package org.ama.berlin.client;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.ama.berlin.dto.MunichCustomer;
import org.ama.berlin.exception.MunichClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MunichClient {
    private static final Logger log = LoggerFactory.getLogger(MunichClient.class);

    private final RestClient munichRestClient;

    @Bulkhead(name = "munich")
    @CircuitBreaker(name = "munich")
    @Retry(name = "munich")
    public List<MunichCustomer> getAllCustomers() {
        log.info("Calling Munich /api/v1/customers");

        List<MunichCustomer> customers = munichRestClient.get()
                .uri("/api/customer/getCustomers")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new MunichClientException("Munich returned 4xx: " + response.getStatusCode());
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new MunichClientException("Munich returned 5xx: " + response.getStatusCode());
                })
                .body(new ParameterizedTypeReference<>() {
                });

        return Objects.requireNonNullElse(customers, List.of());
    }
}
