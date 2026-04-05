package com.br.productservice.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class ProductResumeDTO {
    UUID id;
    String name;
    Double price;
    Integer quantity;
    String description;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
