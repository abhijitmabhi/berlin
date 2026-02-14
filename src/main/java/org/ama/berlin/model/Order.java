package org.ama.berlin.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Order {
    int id;
    String title;
    String description;
    LocalDateTime orderDateTime;
}
