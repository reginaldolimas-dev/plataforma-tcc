package com.br.productservice.data.dao;

import com.br.productservice.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductDao extends JpaRepository<ProductEntity, UUID>, JpaSpecificationExecutor<ProductEntity> {

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
            UPDATE product
            SET active = false
            WHERE id = :id
            """, nativeQuery = true)
    void softDeleteById(UUID id);

}