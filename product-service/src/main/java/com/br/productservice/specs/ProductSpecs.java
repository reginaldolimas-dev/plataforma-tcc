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

            if (filters.name() != null && !filters.name().isBlank()) {
                predicates.add(builder.like(
                    builder.upper(root.get("name")),
                    "%" + filters.name().toUpperCase() + "%"
                ));
            }

            if (filters.description() != null && !filters.description().isBlank()) {
                predicates.add(builder.like(
                    builder.upper(root.get("description")),
                    "%" + filters.description().toUpperCase() + "%"
                ));
            }

            if (filters.minPrice() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("price"), filters.minPrice()));
            }

            if (filters.maxPrice() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("price"), filters.maxPrice()));
            }

            if (filters.quantity() != null) {
                predicates.add(builder.equal(root.get("quantity"), filters.quantity()));
            }

            if (filters.currency() != null && !filters.currency().isBlank()) {
                predicates.add(builder.equal(
                    builder.upper(root.get("currency")),
                    filters.currency().toUpperCase()
                ));
            }

            if (filters.active() != null) {
                predicates.add(builder.equal(root.get("active"), filters.active()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
