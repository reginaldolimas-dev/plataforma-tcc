package com.br.productservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
        T data,
        String message,
        Instant timestamp,
        long elapsed,
        String error
) {

    public static <T> ApiResponse<T> success(T data, String message, long elapsed) {
        return new ApiResponse<>(data, message, Instant.now(), elapsed, null);
    }

    public static <T> ApiResponse<T> error(String message, String error, long elapsed) {
        return new ApiResponse<>(null, message, Instant.now(), elapsed, error);
    }
}