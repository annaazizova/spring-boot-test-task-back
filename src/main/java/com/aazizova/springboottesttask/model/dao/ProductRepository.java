package com.aazizova.springboottesttask.model.dao;

import com.aazizova.springboottesttask.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.id = :productId")
    Product getProductById(@Param("productId") Long productId);

    @Modifying
    @Query("DELETE FROM Product p WHERE p.id = :productId")
    void deleteById(@Param("productId") Long productId);

    @Query("SELECT p FROM Product p WHERE p.quantity < 5")
    List<Product> getLeftovers();
}
