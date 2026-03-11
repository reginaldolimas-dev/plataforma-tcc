package com.br.currencyservice.data.repository;

import com.br.currencyservice.data.dao.CurrencyDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CurrencyRepository {
    private final CurrencyDao currencyDao;
}
