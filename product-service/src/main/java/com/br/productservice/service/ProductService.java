package com.br.productservice.service;

import com.br.productservice.client.CurrencyClient;
import com.br.productservice.data.repository.ProductRepository;
import com.br.productservice.dto.*;
import com.br.productservice.model.entity.ProductEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final CurrencyClient currencyClient;

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public Page<ProductWithPricesDTO> findAllPaginated(ProductFilterDTO filter, Pageable pageable) {
        log.info("Finding all products paginated");
        
        ProductFilterDTO adjustedFilter = adjustFilterForCurrency(filter);

        Page<ProductResumeDTO> result = repository.findAllPaginated(adjustedFilter, pageable);
        log.info("Found {} products", result.getTotalElements());

        Map<String, Double> currencies;

        try {
            currencies = currencyClient.getAllCurrencies();
        } catch (Exception e) {
            log.warn("Failed to fetch currency data, proceeding without currency conversion: {}", e.getMessage());
            currencies = Collections.emptyMap();
        }

        Map<String, Double> finalCurrencies = currencies;
        return result.map(product -> enrichProductWithCurrencies(product, finalCurrencies));
    }
    
    private ProductFilterDTO adjustFilterForCurrency(ProductFilterDTO filter) {
        if (filter.currency() == null || filter.currency().equalsIgnoreCase("BRL")) {
            return filter;
        }

        Double rate = currencyClient.getAllCurrencies().get(filter.currency().toUpperCase());

        if (rate == null){
            return filter;
        }
            
        Double adjustedMin = filter.minPrice() != null ? filter.minPrice() * rate : null;
        Double adjustedMax = filter.maxPrice() != null ? filter.maxPrice() * rate : null;

        return new ProductFilterDTO(adjustedMin, adjustedMax, "BRL", filter.name(), filter.quantity(), filter.description(), filter.active());
    }

    private ProductWithPricesDTO enrichProductWithCurrencies(ProductResumeDTO product, Map<String, Double> currencies) {
        Map<String, Double> pricesInOtherCurrencies = new HashMap<>();
        
        if (product.getPrice() != null && currencies != null && !currencies.isEmpty()) {
            for (Map.Entry<String, Double> entry : currencies.entrySet()) {
                Double priceInCurrency = product.getPrice() / entry.getValue();
                pricesInOtherCurrencies.put(entry.getKey(), priceInCurrency);
            }
        }
        
        return ProductWithPricesDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .active(product.getActive())
                .pricesInOtherCurrencies(pricesInOtherCurrencies)
                .build();
    }

    @Transactional
    public void saveProduct(ProductCreateDTO product) {
        product.setId(generateId());
        log.info("Creating Product");
        repository.saveProduct(product);
        log.info("Product created");
    }

    public ProductEntity findById(UUID id) {
        log.info("Finding Product with id: {}", id);
        ProductEntity product = repository.findById(id);
        log.info("Product found with id: {}", id);
        return product;
    }

    public UUID generateId() {
        return UUID.randomUUID();
    }

    @Transactional
    public void updateProduct(ProductUpdateDTO product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        ProductEntity existingProduct = findById(product.getId());
        
        ProductEntity updatedProduct = updateEntity(product, existingProduct);

        log.info("Updating Product with id: {}", product.getId());
        repository.updateProduct(updatedProduct);
        log.info("Product updated with id: {}", product.getId());
    }

    private ProductEntity updateEntity(ProductUpdateDTO product, ProductEntity existingProduct) {
        if (product.getName() != null) {
            existingProduct.setName(product.getName());
        }
        if (product.getDescription() != null) {
            existingProduct.setDescription(product.getDescription());
        }
        if (product.getPrice() != null) {
            existingProduct.setPrice(product.getPrice());
        }
        if (product.getQuantity() != null) {
            existingProduct.setQuantity(product.getQuantity());
        }
        if (product.getActive() != null) {
            existingProduct.setActive(product.getActive());
        }
        return existingProduct;
    }

    @Transactional
    public void delete(UUID id) {
        log.info("Deleting Product with id: {}", id);
        repository.delete(id);
        log.info("Product deleted with id: {}", id);
    }

    public CountDTO count() {
        return repository.count();
    }
}