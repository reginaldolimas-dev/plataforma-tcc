package com.br.currencyservice.service;

import com.br.currencyservice.dto.CurrencyResponseDTO;

import java.util.List;

public interface CurrencyService {
    List<CurrencyResponseDTO> getAll();

    CurrencyResponseDTO findByCode(String code);
}
