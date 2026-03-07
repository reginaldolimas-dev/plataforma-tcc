package com.br.customerservice.service;

import com.br.customerservice.data.repository.CustomerRepository;
import com.br.customerservice.model.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;


    public CustomerEntity save(CustomerEntity customerEntity) {
        return repository.save(customerEntity);
    }
}
