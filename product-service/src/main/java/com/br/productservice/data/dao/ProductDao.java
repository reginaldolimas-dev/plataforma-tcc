package com.br.productservice.data.dao;

import com.br.productservice.dto.ProductFilterDTO;
import com.br.productservice.dto.ProductResumeDTO;
import com.br.productservice.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductDao extends JpaRepository<ProductEntity, UUID> {

    @Modifying
    @Query(value = """
            UPDATE product
            SET name = :name,
                description = :description,
                price = :price,
                quantity = :quantity
            WHERE id = :id
            """, nativeQuery = true)
    void updateProduct(
            @Param("id") UUID id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("price") Double price,
            @Param("quantity") Integer quantity
    );

    @Modifying
    @Query(value = """
        INSERT INTO product
        (id, name, description, price, quantity)
        VALUES
        (:id, :name, :description, :price, :quantity)
        """, nativeQuery = true)
    void saveProduct(
            @Param("id") UUID id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("price") Double price,
            @Param("quantity") Integer quantity
    );

    @Modifying
    @Query(value = """
            UPDATE product
            SET active = false
            WHERE id = :id
            """, nativeQuery = true)
    void softDeleteById(UUID id);

    @Query("""
        SELECT p FROM ProductEntity p
        WHERE (:#{#filter.name} IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :#{#filter.name}, '%')))
        AND (:#{#filter.description} IS NULL OR LOWER(p.description) LIKE LOWER(CONCAT('%', :#{#filter.description}, '%')))
        AND (:#{#filter.minPrice} IS NULL OR p.price >= :#{#filter.minPrice})
        AND (:#{#filter.maxPrice} IS NULL OR p.price <= :#{#filter.maxPrice})
        AND (:#{#filter.quantity} IS NULL OR p.quantity = :#{#filter.quantity})
    """)
    Page<ProductEntity> findAllFiltered(ProductFilterDTO filter, Pageable pageable);
}