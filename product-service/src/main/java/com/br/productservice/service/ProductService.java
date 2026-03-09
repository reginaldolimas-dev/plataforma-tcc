package com.br.productservice.service;

import com.br.productservice.data.repository.ProductRepository;
import com.br.productservice.dto.ProductCreateDTO;
import com.br.productservice.dto.ProductResumeDTO;
import com.br.productservice.dto.ProductUpdateDTO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public Page<ProductResumeDTO> findAllPaginated(Pageable pageable) {
        return repository.findAllPaginated(pageable);
    }

    public void saveProduct(ProductCreateDTO product) {
        log.info("Creating Product");
        repository.saveProduct(product);
        log.info("Product created");
    }

    public void updateProduct(ProductUpdateDTO product) {
        log.info("Updating Product with id: {}", product.getId());
        repository.updateProduct(product);
        log.info("Product updated with id: {}", product.getId());
    }
}
