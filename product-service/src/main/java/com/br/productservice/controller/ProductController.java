package com.br.productservice.controller;

import com.br.productservice.dto.ProductCreateDTO;
import com.br.productservice.dto.ProductResumeDTO;
import com.br.productservice.dto.ProductUpdateDTO;
import com.br.productservice.service.ProductService;
import lombok.AllArgsConstructor;
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

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<Page<ProductResumeDTO>> findAllPaginated(Pageable pageable) {
        return ResponseEntity.ok(service.findAllPaginated(pageable));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ProductCreateDTO product) {
        service.saveProduct(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ProductUpdateDTO product) {
        product.setId(id);
        service.updateProduct(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
