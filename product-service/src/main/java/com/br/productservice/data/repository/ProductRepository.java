package com.br.productservice.data.repository;

import com.br.productservice.data.dao.ProductDao;
import com.br.productservice.dto.ProductCreateDTO;
import com.br.productservice.dto.ProductResumeDTO;
import com.br.productservice.dto.ProductUpdateDTO;
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

    public void save(ProductCreateDTO product) {
        dao.saveProduct(product);
    }

    public void delete(Long id) {
        dao.deleteById(id);
    }

    public void update(ProductUpdateDTO product) {
        dao.update(product);
    }
}
