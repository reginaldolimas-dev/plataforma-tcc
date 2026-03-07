package com.br.customerservice.controller;

import com.br.customerservice.model.entity.CustomerEntity;
import com.br.customerservice.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
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
    public CustomerEntity buscarPaginado(Pageable pageable) {
        return null;
    }

    @PostMapping
    public CustomerEntity save(@RequestBody CustomerEntity customerEntity) {
        return service.save(customerEntity);
    }

}
