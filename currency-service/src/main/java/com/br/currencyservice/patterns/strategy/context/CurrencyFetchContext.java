package com.br.currencyservice.patterns.strategy.context;

import com.br.currencyservice.patterns.strategy.CurrencyFetchStrategy;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Getter
public class CurrencyFetchContext {
    private final Map<String, CurrencyFetchStrategy> strategies;

    public CurrencyFetchContext(List<CurrencyFetchStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(
                        CurrencyFetchStrategy::getSupportedCode,
                        Function.identity()
                ));
    }

    public CurrencyFetchStrategy getStrategy(String currencyCode) {
        CurrencyFetchStrategy strategy = strategies.get(currencyCode);
        if (strategy == null) {
            throw new IllegalArgumentException(
                    "No strategy found for currency: " + currencyCode
            );
        }
        return strategy;
    }
}
