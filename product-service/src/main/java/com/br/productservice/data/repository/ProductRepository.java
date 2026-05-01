package com.br.productservice.data.repository;

import com.br.productservice.data.dao.ProductDao;
import com.br.productservice.dto.ProductCreateDTO;
import com.br.productservice.dto.ProductFilterDTO;
import com.br.productservice.dto.ProductResumeDTO;
import com.br.productservice.model.entity.ProductEntity;
import com.br.productservice.specs.ProductSpecs;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class ProductRepository {
    private final ProductDao dao;

    public Page<ProductResumeDTO> findAllPaginated(ProductFilterDTO filter, Pageable pageable) {
        Specification<ProductEntity> spec = ProductSpecs.withFilters(filter);

        Page<ProductEntity> entities = dao.findAll(spec, pageable);
        return entities.map(entity -> ProductResumeDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build());
    }

    public void saveProduct(ProductCreateDTO product) {
        dao.saveProduct(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity()
        );
    }

    public void updateProduct(ProductEntity product) {
        dao.updateProduct(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity()
        );
    }

    public ProductEntity findById(UUID id) {
        return dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public void delete(UUID id) {
        dao.softDeleteById(id);
    }
}
