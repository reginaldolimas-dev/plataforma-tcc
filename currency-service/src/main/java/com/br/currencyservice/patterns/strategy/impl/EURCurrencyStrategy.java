package com.br.currencyservice.patterns.strategy.impl;

import com.br.currencyservice.patterns.strategy.CurrencyFetchStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EURCurrencyStrategy implements CurrencyFetchStrategy {
    @Override
    public String getSupportedCode() {
        return "EUR";
    }

    @Override
    public String getEndpointUrl() {
        return "https://economia.awesomeapi.com.br/json/last/EUR-BRL";
    }

    @Override
    public Double extractValue(Map<String, Object> response) {
        Map<String, Object> eurData = (Map<String, Object>) response.get("EURBRL");
        return Double.valueOf((String) eurData.get("bid"));
    }
}
