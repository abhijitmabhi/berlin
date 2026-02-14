package org.ama.berlin.service;

import org.ama.berlin.model.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {
    public Order getOrderById(int id) {
        return Order.builder()
                .id(123)
                .title("Abc")
                .description("Demo Description")
                .orderDateTime(LocalDateTime.now())
                .build();
    }
}