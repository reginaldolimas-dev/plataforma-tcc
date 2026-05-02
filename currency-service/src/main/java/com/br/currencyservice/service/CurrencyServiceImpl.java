package com.br.currencyservice.service;

import com.br.currencyservice.data.repository.CurrencyRepository;
import com.br.currencyservice.dto.CurrencyResponseDTO;
import com.br.currencyservice.model.entity.CurrencyEntity;
import com.br.currencyservice.patterns.strategy.CurrencyFetchStrategy;
import com.br.currencyservice.patterns.strategy.context.CurrencyFetchContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private static final Logger log = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    private static final Set<String> SUPPORTED_CODES =
            Set.of("USD", "EUR", "GBP", "CNY");

    private final CurrencyRepository repository;
    private final CurrencyFetchContext fetchContext;
    private final RestClient restClient;

    @Override
    public List<CurrencyResponseDTO> getAll() {
        refreshIfNeeded();

        return repository.findByCodeIn(SUPPORTED_CODES).stream()
                .map(this::toResponse)
                .toList();
    }

    private CurrencyResponseDTO toResponse(CurrencyEntity currencyEntity) {
        return new CurrencyResponseDTO(
                currencyEntity.getCode(),
                currencyEntity.getValue(),
                currencyEntity.getUpdatedAt()
        );
    }

    @Override
    public CurrencyResponseDTO findByCode(String code) {
        CurrencyFetchStrategy strategy = fetchContext.getStrategy(code);

        String url = getString(code, strategy);

        Map<String, Object> response = getStringObjectMap(url);

        Double value = strategy.extractValue(response);

        return new CurrencyResponseDTO(code, value, LocalDateTime.now());
    }

    private static String getString(String code, CurrencyFetchStrategy strategy) {
        return String.format(strategy.getEndpointUrl(), code);
    }

    private Map<String, Object> getStringObjectMap(String url) {
        return restClient.get()
                .uri(url)
                .retrieve()
                .body(new ParameterizedTypeReference<Map<String, Object>>() {});
    }

    @Transactional
    protected void refreshIfNeeded() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();

        long updatedToday = repository.countUpdatedToday(SUPPORTED_CODES, startOfDay);

        if (updatedToday == SUPPORTED_CODES.size()) {
            return;
        }

        for (String code : SUPPORTED_CODES) {
            CurrencyFetchStrategy strategy = fetchContext.getStrategy(code);
            fetchAndSaveCurrency(strategy, code);
        }
    }

    private void fetchAndSaveCurrency(CurrencyFetchStrategy strategy, String code) {
        try {
            String url = getString(code, strategy);

            Map<String, Object> response = getStringObjectMap(url);

            if (response == null || response.isEmpty()) {
                throw new IllegalStateException("Empty response for " + code);
            }

            Double value = strategy.extractValue(response);

            if (value == null) {
                throw new IllegalStateException("Could not extract value for " + code);
            }

            saveCurrency(code, value);

        } catch (Exception e) {
            Double fallback = strategy.getFallbackValue();
            if (fallback != null) {
                saveCurrency(code, fallback);
                log.warn("Using fallback value for {}: {}", code, fallback);
            } else {
                throw new IllegalStateException("Failed to fetch " + code, e);
            }
        }
    }

    private void saveCurrency(String code, Double value) {
        CurrencyEntity entity = repository.findById(code)
                .orElseGet(CurrencyEntity::new);

        entity.setCode(code);
        entity.setValue(value);
        entity.setUpdatedAt(LocalDateTime.now());

        repository.save(entity);
    }
}
