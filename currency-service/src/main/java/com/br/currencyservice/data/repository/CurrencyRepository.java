package com.br.currencyservice.data.repository;

import com.br.currencyservice.data.dao.CurrencyDao;
import com.br.currencyservice.model.entity.CurrencyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CurrencyRepository {
    private final CurrencyDao currencyDao;

    public List<CurrencyEntity> getAll() {
        return null;
    }
}
