package com.aazizova.springboottesttask.model.dao;

import com.aazizova.springboottesttask.model.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void getProductByIdTest() throws Exception {
        this.testEntityManager.merge(new Product(1, "productName", "productBrand", 1.0, 1L));
        Product product = this.productRepository.productWithId(1L);
        assertTrue(product.getName().equalsIgnoreCase("productName"));
        assertTrue(product.getBrand().equalsIgnoreCase("productBrand"));
        assertEquals(product.getPrice(), 1.0, 0);
        assertEquals(product.getQuantity(), 1L);
    }

    /*@Test
    public void deleteByIdTest() throws Exception {
        long productId = 1;
        Product productToDelete = new Product(productId, "productName", "productBrand", 1.0, 1L);
        testEntityManager.merge(productToDelete);
        productRepository.deleteById(productId);
        assertNull(productRepository.getProductById(productId));
    }*/

    /*@Test
    public void getLeftoversTest() throws Exception {

    }*/
}