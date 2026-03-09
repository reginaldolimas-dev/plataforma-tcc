package com.br.productservice.dto;

public interface ProductResumeDTO {
    Long getId();
    String getName();
    Double getPrice();
    Integer getQuantity();
    String getDescription();
    String getCreatedAt();
    String getUpdatedAt();
}
