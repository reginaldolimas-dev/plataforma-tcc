package com.br.productservice.controller;

import com.br.productservice.dto.ProductCreateDTO;
import com.br.productservice.dto.ProductFilterDTO;
import com.br.productservice.dto.ProductUpdateDTO;
import com.br.productservice.dto.ProductWithPricesDTO;
import com.br.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductService service;

    @GetMapping
    public ResponseEntity<Page<ProductWithPricesDTO>> findAllPaginated(ProductFilterDTO filter, Pageable pageable) {
        log.info("Receiving request to find all products paginated");
        Page<ProductWithPricesDTO> result = service.findAllPaginated(filter, pageable);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ProductCreateDTO product) {
        log.info("Receiving request to save a product");
        service.saveProduct(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody ProductUpdateDTO product) {
        log.info("Receiving request to update product with id: {}", id);
        product.setId(id);
        service.updateProduct(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        log.info("Receiving request to delete product with id: {}", id);
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}