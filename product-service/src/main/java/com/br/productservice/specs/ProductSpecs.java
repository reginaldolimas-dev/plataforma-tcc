package com.br.productservice.specs;

import com.br.productservice.dto.ProductFilterDTO;
import com.br.productservice.model.entity.ProductEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecs {

    public static Specification<ProductEntity> withFilters(ProductFilterDTO filters) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters.getName() != null && !filters.getName().isBlank()) {
                predicates.add(builder.like(
                    builder.upper(root.get("name")),
                    "%" + filters.getName().toUpperCase() + "%"
                ));
            }

            if (filters.getDescription() != null && !filters.getDescription().isBlank()) {
                predicates.add(builder.like(
                    builder.upper(root.get("description")),
                    "%" + filters.getDescription().toUpperCase() + "%"
                ));
            }

            if (filters.getMinPrice() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("price"), filters.getMinPrice()));
            }

            if (filters.getMaxPrice() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("price"), filters.getMaxPrice()));
            }

            if (filters.getQuantity() != null) {
                predicates.add(builder.equal(root.get("quantity"), filters.getQuantity()));
            }

            if (filters.getCurrency() != null && !filters.getCurrency().isBlank()) {
                predicates.add(builder.equal(
                    builder.upper(root.get("currency")),
                    filters.getCurrency().toUpperCase()
                ));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
