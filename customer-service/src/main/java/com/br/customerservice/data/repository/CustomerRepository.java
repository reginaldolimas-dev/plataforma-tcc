package com.br.customerservice.data.repository;

import com.br.customerservice.data.dao.CustomerDao;
import com.br.customerservice.dto.CustomerCreateDTO;
import com.br.customerservice.dto.CustomerResumeDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerRepository {

    private final CustomerDao dao;

    public void save(CustomerCreateDTO customer) {
        dao.insertCustomer(
                customer.getName(),
                customer.getSurname(),
                customer.getEmail(),
                customer.getBirthDate(),
                customer.getActive()
                );
    }

    public Page<CustomerResumeDTO> findAllPaginated(Pageable pageable) {
        return dao.findAllPaginated(pageable);
    }
}
