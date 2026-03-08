package com.br.customerservice.service;

import com.br.customerservice.data.repository.CustomerRepository;
import com.br.customerservice.dto.CustomerCreateDTO;
import com.br.customerservice.dto.CustomerResumeDTO;
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
}
