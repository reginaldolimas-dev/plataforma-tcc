package com.br.currencyservice.controller;

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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currencies")
public class CurrencyController {
    private final CurrencyService service;
    private static final Logger log = LoggerFactory.getLogger(CurrencyController.class);

    @GetMapping
    public ResponseEntity<List<CurrencyResponseDTO>> getAll() {
        log.info("Receiving request to retrieve all currencies");
        List<CurrencyResponseDTO> result = service.getAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{code}")
    public ResponseEntity<CurrencyResponseDTO> findByCode(@PathVariable String code) {
        log.info("Receiving request to find currency by code: {}", code);
        var result = service.findByCode(code);
        return ResponseEntity.ok(result);
    }
}
