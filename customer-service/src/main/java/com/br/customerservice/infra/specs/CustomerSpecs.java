package com.br.customerservice.infra.specs;

import com.br.customerservice.dto.CustomerFilterDTO;
import com.br.customerservice.model.entity.CustomerEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerSpecs {
    public static Specification<CustomerEntity> montarConsulta(CustomerFilterDTO filter) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(filter.name())) {
                predicates.add(builder.like(builder.lower(root.get("name")), "%" + filter.name().toLowerCase() + "%"));
            }

            if (Objects.nonNull(filter.surname())) {
                predicates.add(builder.like(builder.lower(root.get("surname")), "%" + filter.surname().toLowerCase() + "%"));
            }

            if (Objects.nonNull(filter.active())) {
                predicates.add(builder.equal(root.get("active"), filter.active()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
