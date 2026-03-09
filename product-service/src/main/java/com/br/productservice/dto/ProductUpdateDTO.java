package com.br.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
}
