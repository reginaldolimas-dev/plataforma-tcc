package com.br.customerservice.controller;

import com.br.customerservice.dto.CustomerCreateDTO;
import com.br.customerservice.dto.CustomerFilterDTO;
import com.br.customerservice.dto.CustomerResumeDTO;
import com.br.customerservice.dto.CustomerUpdateDTO;
import com.br.customerservice.service.CustomerService;
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

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/customers")
public class CustomerController {

    private final CustomerService service;

    @GetMapping
    public ResponseEntity<Page<CustomerResumeDTO>> findAllPaginated(CustomerFilterDTO filter, Pageable pageable) {
        return ResponseEntity.ok(service.findAllPaginated(filter, pageable));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CustomerCreateDTO customer) {
        service.save(customer);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody CustomerUpdateDTO customer) {
        customer.setId(id);
        service.update(customer);
        return ResponseEntity.ok().build();
    }
}
