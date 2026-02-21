package org.ama.berlin.dto;

public record OrderResponse(
        Integer id,
        String title,
        String description,
        String orderDateTime
) {
    public OrderResponse {
        if (title != null) {
            title = title.toUpperCase();
        }
    }
}
