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
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MunichClient {
    private static final Logger log = LoggerFactory.getLogger(MunichClient.class);

    private final WebClient munichWebClient;

    @Bulkhead(name = "munich")
    @CircuitBreaker(name = "munich")
    @Retry(name = "munich")
    public List<MunichCustomer> getAllCustomers() {
        log.info("Calling Munich /api/v1/customers");

        return munichWebClient.get()
                .uri("/api/customer/getCustomers")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        response.bodyToMono(String.class)
                                .defaultIfEmpty("")
                                .map(body -> new MunichClientException("Munich returned 4xx: " + response.statusCode())))

                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        response.bodyToMono(String.class)
                                .defaultIfEmpty("")
                                .map(body -> new MunichClientException("Munich returned 5xx: " + response.statusCode())))

                .bodyToMono(new ParameterizedTypeReference<List<MunichCustomer>>() {
                })
                .timeout(Duration.ofSeconds(2))
                .block();
    }
}
