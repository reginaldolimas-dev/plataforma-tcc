package com.br.currencyservice.data.dao;

import com.br.currencyservice.model.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyDao extends JpaRepository<CurrencyEntity, String> {
}
