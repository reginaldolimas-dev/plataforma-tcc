package com.br.currencyservice.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CurrencyEntity {
    private String code;
    private Double value;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
