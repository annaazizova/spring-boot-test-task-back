package com.aazizova.springboottesttask.service.impl;

import com.aazizova.springboottesttask.model.dao.ProductRepository;
import com.aazizova.springboottesttask.model.entity.Product;
import com.aazizova.springboottesttask.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> retrieveProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product productWithId(Long productId) {
        return productRepository.getProductById(productId);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public boolean isProductExist(Product product) {
        return productRepository.existsById(product.getId());
    }

    @Override
    public List<Product> retrieveLeftovers() {
        return productRepository.getLeftovers();
    }
}
