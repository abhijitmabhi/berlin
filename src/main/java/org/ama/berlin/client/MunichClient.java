package org.ama.berlin.client;

import lombok.RequiredArgsConstructor;
import org.ama.berlin.dto.MunichCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MunichClient {
    private static final Logger log = LoggerFactory.getLogger(MunichClient.class);

    private final WebClient munichWebClient;

    public List<MunichCustomer> getAllCustomers() {
        log.info("Calling Munich /api/v1/customers");

        return munichWebClient.get()
                .uri("/api/customer/getCustomers")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<MunichCustomer>>() {})
                .timeout(Duration.ofSeconds(3))
                .block();
    }
}
