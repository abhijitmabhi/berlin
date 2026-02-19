package org.ama.berlin.dto;

import java.time.LocalDateTime;

public record OrderResponse(
        Integer id,
        String title,
        String description,
        LocalDateTime orderDateTime
) {
}
