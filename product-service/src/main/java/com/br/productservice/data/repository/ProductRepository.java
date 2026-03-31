package com.br.productservice.data.repository;

import com.br.productservice.data.dao.ProductDao;
import com.br.productservice.dto.ProductCreateDTO;
import com.br.productservice.dto.ProductFilterDTO;
import com.br.productservice.dto.ProductResumeDTO;
import com.br.productservice.model.entity.ProductEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class ProductRepository {
    private final ProductDao dao;

    public Page<ProductResumeDTO> findAllPaginated(ProductFilterDTO filter, Pageable pageable) {
        return dao.findAllPaginated(filter, pageable);
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
