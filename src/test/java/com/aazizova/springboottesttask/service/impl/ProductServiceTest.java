package com.aazizova.springboottesttask.service.impl;

import com.aazizova.springboottesttask.model.dao.ProductRepository;
import com.aazizova.springboottesttask.model.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void products() throws Exception {
        Product product = mock(Product.class);
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(product);
        when(productRepository.findAll()).thenReturn(expectedProducts);
        List<Product> actualProducts = productService.products();
        assertNotNull(actualProducts);
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    public void productWithId() throws Exception {
        Product expectedProduct = mock(Product.class);
        when(productRepository.productWithId(1L)).thenReturn(expectedProduct);
        Product actualProduct = productService.productWithId(1L);
        assertNotNull(actualProduct);
        assertEquals(expectedProduct, actualProduct);
    }
}
