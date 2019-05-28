package com.aazizova.springboottesttask.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @Autowired
    private MockMvc mockMVC;

    @Test
    public void contextLoads() throws Exception {
        assertThat(productController).isNotNull();
    }

    @Test
    @WithMockUser
    public void getAllProductsTest() throws Exception {
        this.mockMVC.perform(get("/api/products"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}