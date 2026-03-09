package com.br.productservice.service;

import com.br.productservice.data.repository.ProductRepository;
import com.br.productservice.dto.ProductCreateDTO;
import com.br.productservice.dto.ProductResumeDTO;
import com.br.productservice.dto.ProductUpdateDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public Page<ProductResumeDTO> findAllPaginated(Pageable pageable) {
        return repository.findAllPaginated(pageable);
    }

    public void save(ProductCreateDTO product) {
        repository.save(product);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    public void update(ProductUpdateDTO product) {
        repository.update(product);
    }
}
