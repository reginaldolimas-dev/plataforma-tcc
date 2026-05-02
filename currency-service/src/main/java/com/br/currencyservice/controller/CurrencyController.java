package com.br.currencyservice.controller;

import com.br.currencyservice.dto.ApiResponse;
import com.br.currencyservice.dto.CurrencyResponseDTO;
import com.br.currencyservice.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {
    private final CurrencyService service;
    private static final Logger log = LoggerFactory.getLogger(CurrencyController.class);

    @GetMapping
    public ResponseEntity<ApiResponse<List<CurrencyResponseDTO>>> getAll() {
        log.info("Receiving request to retrieve all currencies");

        long startTime = System.currentTimeMillis();
        List<CurrencyResponseDTO> result = service.getAll();
        long elapsed = System.currentTimeMillis() - startTime;

        ApiResponse<List<CurrencyResponseDTO>> response = ApiResponse.<List<CurrencyResponseDTO>>builder()
                .message("Currency retrieved successfully")
                .timestamp(LocalDateTime.now())
                .elapsed(elapsed)
                .data(result)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{code}")
    public ResponseEntity<ApiResponse<CurrencyResponseDTO>> findByCode(@PathVariable String code) {
        log.info("Receiving request to find currency by code: {}", code);

        long startTime = System.currentTimeMillis();
        var result = service.findByCode(code);
        long elapsed = System.currentTimeMillis() - startTime;

        ApiResponse<CurrencyResponseDTO> response = ApiResponse.<CurrencyResponseDTO>builder()
                .message("Currency retrieved successfully")
                .timestamp(LocalDateTime.now())
                .elapsed(elapsed)
                .data(result)
                .build();

        return ResponseEntity.ok(response);
    }
}
