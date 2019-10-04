package com.aazizova.springboottesttask.model.dao;

import com.aazizova.springboottesttask.model.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testGetProductById() throws Exception {
        testEntityManager.merge(new Product(1, "productName", "productBrand", 1.0, 1L));
        Product product = productRepository.productWithId(1L);
        assertTrue(product.getName().equalsIgnoreCase("productName"));
        assertTrue(product.getBrand().equalsIgnoreCase("productBrand"));
        assertEquals(product.getPrice(), 1.0, 0);
        assertEquals(product.getQuantity(), 1L);
    }

    @Test
    public void testDeleteById() throws Exception {
        testEntityManager.merge(new Product(1, "productName", "productBrand", 1.0, 1L));
        productRepository.deleteById(1L);
        assertNull(productRepository.productWithId(1L));
    }
}