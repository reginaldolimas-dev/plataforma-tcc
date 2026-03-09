package com.br.productservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductCreateDTO {
    private UUID id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
}
