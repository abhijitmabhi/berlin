package org.ama.berlin.mapper;

import org.ama.berlin.dto.OrderResponse;
import org.ama.berlin.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "orderDateTime", expression = "java(formatInstant(order.getOrderDateTime()))")
    OrderResponse toResponse(Order order);

    default String formatInstant(Instant value) {
        if (value == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.of("Europe/Berlin"));
        return formatter.format(value);
    }
}
