package com.aazizova.springboottesttask.model.dao;

import com.aazizova.springboottesttask.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Product repository.
 */
@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Product with id.
     *
     * @param id Long
     *
     * @return Product
     */
    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Product productWithId(@Param("id") Long id);

    /**
     * Delete product with id.
     *
     * @param id Long
     */
    @Modifying
    @Query("DELETE FROM Product p WHERE p.id = :id")
    void deleteById(@Param("id") Long id);

    /**
     * Leftovers.
     *
     * @return List<Product>
     */
    @Query("SELECT p FROM Product p WHERE p.quantity < 5")
    List<Product> leftovers();
}
