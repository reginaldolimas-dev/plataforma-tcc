package com.br.productservice.dto;

import lombok.Data;

@Data
public class CurrencyResponseDTO {
    private String code;
    private Double value;
    private String createdAt;
}