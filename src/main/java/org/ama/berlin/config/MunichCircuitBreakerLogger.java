package org.ama.berlin.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.stereotype.Component;

@Component
public class MunichCircuitBreakerLogger {
    public MunichCircuitBreakerLogger(CircuitBreakerRegistry registry) {
        registry.circuitBreaker("munich")
                .getEventPublisher()
                .onStateTransition(e ->
                        System.out.println("#### CB State transition: " + e.getStateTransition()));
    }
}
