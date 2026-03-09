package com.br.productservice.data.dao;

import com.br.productservice.dto.ProductCreateDTO;
import com.br.productservice.dto.ProductResumeDTO;
import com.br.productservice.dto.ProductUpdateDTO;
import com.br.productservice.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<ProductEntity, Long> {
    Page<ProductResumeDTO> findAllPaginated(Pageable pageable);

    void update(ProductUpdateDTO product);

    void saveProduct(ProductCreateDTO product);
}
