package com.aazizova.springboottesttask.service;

import com.aazizova.springboottesttask.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> retrieveProducts();

    Product productWithId(Long productId);

    void addProduct(Product product);

    void deleteProductById(Long productId);

    void updateProduct(Product product);

    boolean isProductExist(Product product);

    List<Product> retrieveLeftovers();
}
