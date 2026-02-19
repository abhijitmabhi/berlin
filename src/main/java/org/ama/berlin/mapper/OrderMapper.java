package org.ama.berlin.mapper;

import org.ama.berlin.dto.OrderResponse;
import org.ama.berlin.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse toResponse(Order order);
}
