package com.aazizova.springboottesttask.controller;

import com.aazizova.springboottesttask.model.entity.Product;
import com.aazizova.springboottesttask.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Anna on 02.02.2019.
 */
@RestController
public class Controller {
    private static final Logger logger = LogManager.getLogger(Controller.class);

    @Value("${spring.application.name}")
    String appName;

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String homePage() {
        return appName;
    }

    @GetMapping("/api/products")
    public List<Product> getProducts() {
        List<Product> products = productService.retrieveProducts();
        return products;
    }

    @GetMapping("/api/products/{productId}")
    public Product getEmployee(@PathVariable(name="productId")Long productId) {
        return productService.getProduct(productId);
    }

    @PostMapping("/api/products")
    public void saveProduct(@RequestBody Product product){
        productService.saveProduct(product);
        logger.info("Product Saved Successfully");
    }

    @DeleteMapping("/api/products/{productId}")
    public void deleteProduct(@PathVariable(name="productId")Long productId){
        productService.deleteProduct(productId);
        logger.info("Product Deleted Successfully");
    }

    @PutMapping("/api/products/{productId}")
    public void updateProduct(@RequestBody Product product,
                               @PathVariable(name="employeeId")Long productId){
        Product prod = productService.getProduct(productId);
        if(prod != null){
            productService.updateProduct(product);
        }
        logger.info("Product Updated Successfully");
    }
}
