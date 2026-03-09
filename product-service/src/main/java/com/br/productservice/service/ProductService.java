package com.br.productservice.service;

import com.br.productservice.data.repository.ProductRepository;
import com.br.productservice.dto.ProductCreateDTO;
import com.br.productservice.dto.ProductResumeDTO;
import com.br.productservice.dto.ProductUpdateDTO;
import com.br.productservice.model.entity.ProductEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public Page<ProductResumeDTO> findAllPaginated(Pageable pageable) {
        return repository.findAllPaginated(pageable);
    }
    @Transactional
    public void saveProduct(ProductCreateDTO product) {
        product.setId(generateId());
        log.info("Creating Product");
        repository.saveProduct(product);
        log.info("Product created");
    }

    public ProductEntity findById(UUID id) {
        return repository.findById(id);
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
        return existingProduct;
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }
}
