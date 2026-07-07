package com.br.productservice.dto;

public record ProductFilterDTO(
    Double minPrice,
    Double maxPrice,
    String currency,
    String name,
    Integer quantity,
    String description,
    Boolean active)
{}
