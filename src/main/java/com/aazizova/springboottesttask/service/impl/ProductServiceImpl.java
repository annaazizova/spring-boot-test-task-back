package com.aazizova.springboottesttask.service.impl;

import com.aazizova.springboottesttask.model.dao.ProductRepository;
import com.aazizova.springboottesttask.model.entity.Product;
import com.aazizova.springboottesttask.service.ProductService;
import com.aazizova.springboottesttask.utils.ProductUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Product service impl.
 */
@Service
public final class ProductServiceImpl implements ProductService {
    /**
     * Product repository.
     */
    @Autowired
    private ProductRepository productRepository;

    /**
     * Product utils.
     */
    @Autowired
    private ProductUtils productUtils;

    @Override
    public List<Product> products() {
        return productRepository.findAll();
    }

    @Override
    public Product productWithId(final Long id) {
        return productRepository.productWithId(id);
    }

    @Override
    public void addProduct(final Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(final Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void updateProduct(final Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> leftovers() {
        return productRepository.leftovers();
    }

    @Override
    public boolean exportToXLSX(final List<Long> productIds) {
        List<Product> products = new ArrayList<>();
        for (Long productId : productIds) {
            products.add(productRepository.productWithId(productId));
        }
        return productUtils.exportToXLSX(products);
    }
}
