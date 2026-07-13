package com.br.customerservice.service;

import com.br.customerservice.data.repository.CustomerRepository;
import com.br.customerservice.dto.*;
import com.br.customerservice.model.entity.CustomerEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Transactional
    public void save(CustomerCreateDTO customer) {
        log.info("Creating customer");
        repository.save(customer);
        log.info("Customer created");
    }

    public Page<CustomerResumeDTO> findAllPaginated(CustomerFilterDTO filter, Pageable pageable) {
        log.info("Finding all customers paginated");
        Page<CustomerResumeDTO> modelos = repository.findAllPaginated(filter, pageable);
        log.info("Customers found");
        return modelos;
    }

    @Transactional
    public void delete(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        log.info("Deleting customer with id: {}", id);
        repository.delete(id);
        log.info("Customer deleted with id: {}", id);
    }

    public CustomerEntity findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public void update(CustomerUpdateDTO customer) {
        CustomerEntity existingCustomer = findById(customer.getId());

        CustomerEntity entity = updateEntity(customer, existingCustomer);

        log.info("Updating customer with id: {}", customer.getId());
        repository.update(entity);
        log.info("Customer updated with id: {}", customer.getId());
    }

    private CustomerEntity updateEntity(CustomerUpdateDTO customer, CustomerEntity existingCustomer) {
        if (customer.getName() != null) {
            existingCustomer.setName(customer.getName());
        }
        if (customer.getSurname() != null) {
            existingCustomer.setSurname(customer.getSurname());
        }
        if (customer.getEmail() != null) {
            existingCustomer.setEmail(customer.getEmail());
        }
        if (customer.getBirthDate() != null) {
            existingCustomer.setBirthDate(customer.getBirthDate());
        }
        if (customer.getActive() != null) {
            existingCustomer.setActive(customer.getActive());
        }

        return existingCustomer;
    }

    public CountDTO count() {
        return repository.count();
    }
}
