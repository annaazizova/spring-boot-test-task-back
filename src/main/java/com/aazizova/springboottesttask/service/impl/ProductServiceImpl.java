package com.aazizova.springboottesttask.service.impl;

import com.aazizova.springboottesttask.model.dao.ProductRepository;
import com.aazizova.springboottesttask.model.entity.Product;
import com.aazizova.springboottesttask.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Anna on 03.02.2019.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> retrieveProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Product getProduct(Long productId) {
        Optional<Product> optProduct = productRepository.findById(productId);
        return optProduct.get();
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }
}
