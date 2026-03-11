package com.br.currencyservice.service;

import com.br.currencyservice.data.repository.CurrencyRepository;
import com.br.currencyservice.model.entity.CurrencyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRepository repository;

    public List<CurrencyEntity> getAll() {
        return repository.getAll();
    }
}
