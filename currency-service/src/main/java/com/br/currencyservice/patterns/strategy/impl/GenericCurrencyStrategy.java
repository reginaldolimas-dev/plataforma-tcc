package com.br.currencyservice.patterns.strategy.impl;

import com.br.currencyservice.patterns.strategy.CurrencyFetchStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GenericCurrencyStrategy implements CurrencyFetchStrategy {
    @Override
    public String getSupportedCode() {
        return "GENERIC";
    }

    @Override
    public String getEndpointUrl() {
        return "https://economia.awesomeapi.com.br/json/last/%s-BRL";
    }

    @Override
    public Double extractValue(Map<String, Object> response) {
        return null;
    }

}
