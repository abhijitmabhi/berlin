package org.ama.berlin.service;

import lombok.RequiredArgsConstructor;
import org.ama.berlin.dto.MunichCustomer;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MunichCustomerService {
    private final WebClient munichWebClient;

    public List<MunichCustomer> getAllCustomers() {
        return munichWebClient.get()
                .uri("/api/customer/getCustomers")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<MunichCustomer>>() {})
                .block();
    }
}
