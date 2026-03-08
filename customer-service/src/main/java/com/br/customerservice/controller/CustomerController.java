package com.br.customerservice.controller;

import com.br.customerservice.dto.CustomerResumeDTO;
import com.br.customerservice.model.entity.CustomerEntity;
import com.br.customerservice.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService service;

    @GetMapping
    public ResponseEntity<Page<CustomerResumeDTO>> findAllPaginated(Pageable pageable) {
        return ResponseEntity.ok(service.findAllPaginated(pageable));
    }

    @PostMapping
    public ResponseEntity<CustomerEntity> save(@RequestBody CustomerEntity customerEntity) {
        return ResponseEntity.ok(service.save(customerEntity));
    }

}
