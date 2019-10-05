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
@DataJpaTest(properties = {"spring.datasource.initialization-mode=never"})
public class ProductRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testDeleteById() throws Exception {
        testEntityManager.merge(new Product(2L, "productName2", "productBrand2", 2.0, 2L));
        productRepository.deleteById(2L);
        assertNull(productRepository.productWithId(2L));
    }

    @Test
    public void testGetLeftovers() throws Exception {
        testEntityManager.merge(new Product(3L, "productName3", "productBrand3", 3.0, 5L));
        testEntityManager.merge(new Product(4L, "productName4", "productBrand4", 4.0, 2L));
        List<Product> products = productRepository.leftovers();
        assertNotNull(products);
        assertEquals(1, products.size());
    }
}