package com.aazizova.springboottesttask.service;

import com.aazizova.springboottesttask.model.entity.Product;

import java.util.List;

/**
 * Created by Anna on 03.02.2019.
 */
public interface ProductService {
    List<Product> retrieveProducts();

    Product getProduct(Long productId);

    void saveProduct(Product product);

    void deleteProduct(Long productId);

    void updateProduct(Product product);
}
