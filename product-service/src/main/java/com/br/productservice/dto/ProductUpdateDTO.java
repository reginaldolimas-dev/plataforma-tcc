package com.br.productservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductUpdateDTO {
    private UUID id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private Boolean active;
}
