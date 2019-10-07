package com.aazizova.springboottesttask.utils.builder;

import com.aazizova.springboottesttask.model.entity.Product;
import com.google.code.siren4j.component.Link;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class CustomLinkBuilderTest {
    @Autowired
    private CustomLinkBuilder customLinkBuilder;

    private Product product;
    private HttpServletRequest httpServletRequest;

    @Before
    public void setUp() throws Exception {
        product = mock(Product.class);
        when(product.getId()).thenReturn(1L);
        httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getRequestURI()).thenReturn("requestURI");
    }

    @Test
    public void productLink() throws Exception {
        Link productLink = customLinkBuilder.productLink(product, httpServletRequest);
        assertNull(productLink.getTitle());
        assertNull(productLink.getType());
        assertEquals("requestURI/1", productLink.getHref());
        assertNotNull(productLink.getRel());
        assertEquals(1, productLink.getRel().length);
        assertEquals("self", productLink.getRel()[0]);
    }
}
