package com.br.productservice.dto;

import lombok.Value;

@Value
public class ProductFilterDTO {
    Double minPrice;
    Double maxPrice;
    String currency;
    String name;
    Integer quantity;
    String description;
}
