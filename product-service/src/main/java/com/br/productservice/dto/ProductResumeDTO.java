package com.br.productservice.dto;

import java.util.UUID;

public interface ProductResumeDTO {
    UUID getId();
    String getName();
    Double getPrice();
    Integer getQuantity();
    String getDescription();
    String getCreatedAt();
    String getUpdatedAt();
}
