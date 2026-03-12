package com.br.currencyservice.controller;

import com.br.currencyservice.dto.CurrencyResponseDTO;
import com.br.currencyservice.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {
    private final CurrencyService service;

    @GetMapping
    public ResponseEntity<List<CurrencyResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{code}")
    public ResponseEntity<CurrencyResponseDTO> findByCode(@PathVariable String code) {
        return ResponseEntity.ok(service.findByCode(code));
    }
}
