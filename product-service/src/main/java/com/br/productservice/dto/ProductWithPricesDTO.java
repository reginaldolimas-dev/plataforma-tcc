package com.br.productservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class ProductWithPricesDTO {
    private UUID id;
    private String name;
    private Double price;
    private Integer quantity;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Map<String, Double> pricesInOtherCurrencies;
    private Boolean active;
}