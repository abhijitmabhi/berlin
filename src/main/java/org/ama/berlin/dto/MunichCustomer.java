package org.ama.berlin.dto;

public record MunichCustomer(
        Integer id,
        String name,
        String address,
        String email
) {
}
