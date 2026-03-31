package com.br.productservice.dto;

import lombok.Value;

@Value
public class ProductFilterDTO {
    String price;
    String name;
    String description;
    Integer quantity;
}
