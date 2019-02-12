package com.aazizova.springboottesttask.model.dao;

import com.aazizova.springboottesttask.model.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Anna on 12.02.2019.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testGetProductById() throws Exception {
        this.testEntityManager.merge(new Product(1, "productName", "productBrand", 1.0, 1L));
        Product product = this.productRepository.getProductById(1L);
        assertTrue(product.getName().equalsIgnoreCase("productName"));
        assertTrue(product.getBrand().equalsIgnoreCase("productBrand"));
        assertEquals(product.getPrice(), 1.0, 0);
        assertEquals(product.getQuantity(), 1L);
    }
}