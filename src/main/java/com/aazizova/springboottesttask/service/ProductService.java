package com.aazizova.springboottesttask.service;

import com.aazizova.springboottesttask.model.entity.Product;

import java.util.List;

/**
 * Product service.
 */
public interface ProductService {
    /**
     * Products.
     *
     * @return List<Product>
     */
    List<Product> products();

    /**
     * Product with id.
     *
     * @param id Long
     * @return Product
     */
    Product productWithId(Long id);

    /**
     * Add product.
     *
     * @param product Product
     */
    void addProduct(Product product);

    /**
     * Delete product with id.
     *
     * @param id Long
     */
    void deleteProductById(Long id);

    /**
     * Update product.
     *
     * @param product Product
     */
    void updateProduct(Product product);

    /**
     * Leftovers.
     *
     * @return List<Product>
     */
    List<Product> leftovers();

    /**
     * Export to XLSX file.
     *
     * @param productIds List<Long>
     * @return boolean
     */
    boolean exportToXLSX(List<Long> productIds);
}
