package com.br.customerservice.service;

import com.br.customerservice.data.repository.CustomerRepository;
import com.br.customerservice.dto.CustomerCreateDTO;
import com.br.customerservice.dto.CustomerResumeDTO;
import com.br.customerservice.dto.CustomerUpdateDTO;
import com.br.customerservice.model.entity.CustomerEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    @Transactional
    public void save(CustomerCreateDTO customer) {
        repository.save(customer);
    }

    public Page<CustomerResumeDTO> findAllPaginated(Pageable pageable) {
        return repository.findAllPaginated(pageable);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    public CustomerEntity findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public void update(CustomerUpdateDTO customer) {
        CustomerEntity existingCustomer = repository.findById(customer.getId());

        CustomerEntity entity = updateEntity(customer, existingCustomer);

        repository.update(entity);
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
}
