package com.aazizova.springboottesttask.model.dao;

import com.aazizova.springboottesttask.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Anna on 02.02.2019.
 */
@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
