package com.br.productservice.data.repository;

import com.br.productservice.data.dao.ProductDao;
import com.br.productservice.dto.ProductCreateDTO;
import com.br.productservice.dto.ProductResumeDTO;
import com.br.productservice.model.entity.ProductEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductRepository {
    private final ProductDao dao;

    public Page<ProductResumeDTO> findAllPaginated(Pageable pageable) {
        return dao.findAllPaginated(pageable);
    }

    public void saveProduct(ProductCreateDTO product) {
        dao.saveProduct(
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

    public ProductEntity findById(Long id) {
        return dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public void delete(Long id) {
        dao.softDeleteById(id);
    }
}
