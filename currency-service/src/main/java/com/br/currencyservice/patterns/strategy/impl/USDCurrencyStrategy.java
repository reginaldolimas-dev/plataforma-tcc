package com.br.currencyservice.patterns.strategy.impl;

import com.br.currencyservice.patterns.strategy.CurrencyFetchStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class USDCurrencyStrategy implements CurrencyFetchStrategy {
    @Override
    public String getSupportedCode() {
        return "USD";
    }

    @Override
    public String getEndpointUrl() {
        return "https://economia.awesomeapi.com.br/json/last/USD-BRL";
    }

    @Override
    public Double extractValue(Map<String, Object> response) {
        Map<String, Object> data = (Map<String, Object>) response.get("USDBRL");
        return Double.valueOf((String) data.get("bid"));
    }
}
