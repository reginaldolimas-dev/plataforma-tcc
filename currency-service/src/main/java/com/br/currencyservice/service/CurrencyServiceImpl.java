package com.br.currencyservice.service;

import com.br.currencyservice.data.repository.CurrencyRepository;
import com.br.currencyservice.dto.CurrencyResponseDTO;
import com.br.currencyservice.dto.ExternalCurrencyDTO;
import com.br.currencyservice.model.entity.CurrencyEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

    private static final Set<String> SUPPORTED_CODES = Set.of("USD", "EUR", "GBP", "CNY");
    private static final String EXTERNAL_URL = "https://economia.awesomeapi.com.br/json/last/USD-BRL,EUR-BRL,GBP-BRL,CNY-BRL";

    private final CurrencyRepository repository;
    private final RestClient restClient;

    @Override
    public List<CurrencyResponseDTO> getAll() {
        refreshIfNeeded();

        return repository.findByCodeIn(SUPPORTED_CODES).stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public CurrencyResponseDTO findByCode(String code) {
        return null;
    }

    @Transactional
    protected void refreshIfNeeded() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();

        long updatedToday = repository.countUpdatedToday(SUPPORTED_CODES, startOfDay);

        if (updatedToday == SUPPORTED_CODES.size()) {
            return;
        }

        Map<String, ExternalCurrencyDTO> response = restClient.get()
                .uri(EXTERNAL_URL)
                .retrieve()
                .body(new org.springframework.core.ParameterizedTypeReference<Map<String, ExternalCurrencyDTO>>() {});

        if (response == null || response.isEmpty()) {
            throw new IllegalStateException("Failed to fetch currency data from external API");
        }

        saveCurrency(response, "USD");
        saveCurrency(response, "EUR");
        saveCurrency(response, "GBP");
        saveCurrency(response, "CNY");
    }

    private void saveCurrency(Map<String, ExternalCurrencyDTO> response, String code) {

        String pair = code + "BRL";

        ExternalCurrencyDTO external = response.get(pair);


        if (external == null || external.getBid() == null) {
            throw new IllegalStateException("Currency not returned by external API: " + code);
        }

        CurrencyEntity entity = repository.findById(code)
                .orElseGet(CurrencyEntity::new);

        entity.setCode(code);
        entity.setValue(Double.valueOf(external.getBid()));
        entity.setUpdatedAt(LocalDateTime.now());

        repository.save(entity);
    }

    private CurrencyResponseDTO toResponse(CurrencyEntity entity) {
        return new CurrencyResponseDTO(
                entity.getCode(),
                entity.getValue(),
                entity.getUpdatedAt()
        );
    }
}
