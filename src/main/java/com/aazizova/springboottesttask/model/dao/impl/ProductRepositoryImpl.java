package com.aazizova.springboottesttask.model.dao.impl;

import com.aazizova.springboottesttask.model.dao.ProductRepository;
import com.aazizova.springboottesttask.model.entity.Product;

import java.util.List;
import java.util.Optional;

/**
 * Created by Anna on 03.02.2019.
 */
public abstract class ProductRepositoryImpl implements ProductRepository {
    @Override
    public Optional<Product> findById(Long aLong) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
