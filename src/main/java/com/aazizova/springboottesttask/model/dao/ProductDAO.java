package com.aazizova.springboottesttask.model.dao;

import com.aazizova.springboottesttask.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Anna on 02.02.2019.
 */
@Transactional
public interface ProductDAO extends JpaRepository<Product, Long> {
}
