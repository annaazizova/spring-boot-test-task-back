package com.aazizova.springboottesttask.controller;

import com.aazizova.springboottesttask.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


/**
 * Created by Anna on 12.02.2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
    @Autowired
    private Controller controller;

    @Autowired
    private org.springframework.test.web.servlet.MockMvc mockMVC;

    @MockBean
    private ProductService productService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void returnAppName() throws Exception {
        this.mockMVC.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/")).andExpect(content().string("Spring Boot Test Task"));
    }

    @Test
    public void getAllProductsTest() throws Exception {

    }
}