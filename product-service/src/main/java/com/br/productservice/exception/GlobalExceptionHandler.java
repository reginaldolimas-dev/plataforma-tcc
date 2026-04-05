package com.br.productservice.exception;

import com.br.productservice.dto.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusiness(BusinessException ex) {
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .message("Business rule violation")
                .timestamp(LocalDateTime.now())
                .error(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalState(IllegalStateException ex) {
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .message("Illegal state")
                .timestamp(LocalDateTime.now())
                .error(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(EntityNotFoundException ex) {
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .message("Resource not found")
                .timestamp(LocalDateTime.now())
                .error(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex) {
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .message("An unexpected error occurred")
                .timestamp(LocalDateTime.now())
                .error(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}