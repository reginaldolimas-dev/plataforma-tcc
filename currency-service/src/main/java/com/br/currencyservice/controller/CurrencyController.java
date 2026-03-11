package com.br.currencyservice.controller;

import com.br.currencyservice.model.entity.CurrencyEntity;
import com.br.currencyservice.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {
    private final CurrencyService service;

    @GetMapping
    public List<CurrencyEntity> getAll() {
        return service.getAll();
    }
}
