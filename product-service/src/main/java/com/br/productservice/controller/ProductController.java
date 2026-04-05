package com.br.productservice.controller;

import com.br.productservice.dto.ApiResponse;
import com.br.productservice.dto.ProductCreateDTO;
import com.br.productservice.dto.ProductFilterDTO;
import com.br.productservice.dto.ProductWithPricesDTO;
import com.br.productservice.dto.ProductUpdateDTO;
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

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductService service;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ProductWithPricesDTO>>> findAllPaginated(ProductFilterDTO filter, Pageable pageable) {
        log.info("Receiving request to find all products paginated");
        long startTime = System.currentTimeMillis();

        Page<ProductWithPricesDTO> result = service.findAllPaginated(filter, pageable);

        long elapsed = System.currentTimeMillis() - startTime;
        ApiResponse<Page<ProductWithPricesDTO>> response = ApiResponse.<Page<ProductWithPricesDTO>>builder()
                .message("Products retrieved successfully")
                .timestamp(LocalDateTime.now())
                .elapsed(elapsed)
                .data(result)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> save(@RequestBody ProductCreateDTO product) {
        log.info("Receiving request to save a product");
        long startTime = System.currentTimeMillis();

        service.saveProduct(product);

        long elapsed = System.currentTimeMillis() - startTime;
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .message("Product saved successfully")
                .timestamp(LocalDateTime.now())
                .elapsed(elapsed)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable UUID id, @RequestBody ProductUpdateDTO product) {
        log.info("Receiving request to update product with id: {}", id);
        long startTime = System.currentTimeMillis();

        product.setId(id);
        service.updateProduct(product);

        long elapsed = System.currentTimeMillis() - startTime;
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .message("Product updated successfully")
                .timestamp(LocalDateTime.now())
                .elapsed(elapsed)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        log.info("Receiving request to delete product with id: {}", id);
        long startTime = System.currentTimeMillis();

        service.delete(id);

        long elapsed = System.currentTimeMillis() - startTime;
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .message("Product deleted successfully")
                .timestamp(LocalDateTime.now())
                .elapsed(elapsed)
                .build();

        return ResponseEntity.ok(response);
    }
}