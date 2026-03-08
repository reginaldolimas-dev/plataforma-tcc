package com.br.customerservice.data.dao;

import com.br.customerservice.dto.CustomerResumeDTO;
import com.br.customerservice.model.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CustomerDao extends JpaRepository<CustomerEntity, Long> {

    Page<CustomerResumeDTO> findAllBy(Pageable pageable);

    @Query(value = """
            SELECT
               c.id as id,
               c.name as name,
               c.surname as surname,
               c.email as email,
               c.birth_date as birthDate,
               c.active as active
            FROM customer c
            """,
            countQuery = """
                            SELECT count(*) FROM customer c
                        """,
            nativeQuery = true)
    Page<CustomerResumeDTO> findAllPaginated(Pageable pageable);

    @Modifying
    @Query(value = """
            INSERT INTO customer
            (name, surname, email, birth_date, active)
            VALUES
            (:name, :surname, :email, :birthDate, :active)
            """, nativeQuery = true)
    void insertCustomer(
            String name,
            String surname,
            String email,
            LocalDate birthDate,
            boolean active
    );

}
