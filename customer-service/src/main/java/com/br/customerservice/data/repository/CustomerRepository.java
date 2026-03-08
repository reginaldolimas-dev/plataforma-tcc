package com.br.customerservice.data.repository;

import com.br.customerservice.data.dao.CustomerDao;
import com.br.customerservice.dto.CustomerResumeDTO;
import com.br.customerservice.model.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerRepository {

    private final CustomerDao dao;

    public CustomerEntity save(CustomerEntity customerEntity) {
        return dao.save(customerEntity);
    }

    public Page<CustomerResumeDTO> findAllPaginated(Pageable pageable) {
        return dao.findAllPaginated(pageable);
    }
}
