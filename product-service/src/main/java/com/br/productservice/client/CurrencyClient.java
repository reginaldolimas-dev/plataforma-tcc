package com.br.productservice.client;

import com.br.productservice.dto.ApiResponse;
import com.br.productservice.dto.CurrencyResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CurrencyClient {

    private final RestTemplate restTemplate;
    private final String currencyServiceUrl;

    public CurrencyClient(RestTemplate restTemplate, @Value("${currency-service.url:http://localhost:8081}") String currencyServiceUrl) {
        this.restTemplate = restTemplate;
        this.currencyServiceUrl = currencyServiceUrl;
    }

    public Map<String, Double> getAllCurrencies() {
        try {
            ResponseEntity<ApiResponse<List<CurrencyResponseDTO>>> response = restTemplate.exchange(
                    currencyServiceUrl + "/currencies",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ApiResponse<List<CurrencyResponseDTO>>>() {}
            );

            if (response.getBody() != null && response.getBody().getData() != null) {
                return response.getBody().getData().stream()
                        .collect(Collectors.toMap(CurrencyResponseDTO::getCode, CurrencyResponseDTO::getValue));
            }
        } catch (Exception e) {
        }
        return Collections.emptyMap();
    }
}