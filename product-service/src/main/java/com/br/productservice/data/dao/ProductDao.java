package com.br.productservice.data.dao;

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

    @Query(value = """
            SELECT
                p.id as id,
                p.name  as name,
                p.description as description,
                p.price as price,
                p.quantity as quantity,
                p.created_at as createdAt,
                p.updated_at as updatedAt
                FROM product p
            """,
            countQuery = """
                            SELECT count(*) FROM product p
                            """,
            nativeQuery = true)
    Page<ProductResumeDTO> findAllPaginated(Pageable pageable);

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
}
