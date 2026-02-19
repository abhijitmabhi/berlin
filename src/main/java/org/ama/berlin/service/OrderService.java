package org.ama.berlin.service;

import lombok.RequiredArgsConstructor;
import org.ama.berlin.dto.OrderResponse;
import org.ama.berlin.mapper.OrderMapper;
import org.ama.berlin.model.Order;
import org.ama.berlin.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderResponse getOrderById(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order not found: " + id
                ));
        return orderMapper.toResponse(order);
    }
}
