package com.br.customerservice.data.repository;

import com.br.customerservice.data.dao.CustomerDao;
import com.br.customerservice.dto.CustomerCreateDTO;
import com.br.customerservice.dto.CustomerResumeDTO;
import com.br.customerservice.dto.CustomerUpdateDTO;
import com.br.customerservice.model.entity.CustomerEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;

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

    public void delete(Long id) {
        if (id != null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        dao.softDeleteById(id);
    }

    public void update(CustomerEntity customer) {
        dao.updateCustumer(
                        customer.getId(),
                        customer.getName(),
                        customer.getSurname(),
                        customer.getEmail(),
                        customer.getBirthDate(),
                        customer.getActive()
                );
    }

    public CustomerEntity findById(Long id) {
        return dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
    }
}
