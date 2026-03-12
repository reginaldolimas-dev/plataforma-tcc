package com.br.currencyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CurrencyResponseDTO {
    private String code;
    private Double value;
    private LocalDateTime updatedAt;
}
