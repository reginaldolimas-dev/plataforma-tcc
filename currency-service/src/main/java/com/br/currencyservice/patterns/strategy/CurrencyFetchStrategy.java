package com.br.currencyservice.patterns.strategy;

import java.util.Map;

public interface CurrencyFetchStrategy {

    String getSupportedCode();

    String getEndpointUrl();

    Double extractValue(Map<String, Object> response);

    default Double getFallbackValue() {
        return null;
    }
}
