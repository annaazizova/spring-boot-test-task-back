package com.aazizova.springboottesttask.controller;

import com.aazizova.springboottesttask.model.entity.Product;
import com.aazizova.springboottesttask.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Anna on 02.02.2019.
 */
@RestController
@Log4j2
@CrossOrigin(origins = "http://localhost:3000")
public class Controller {
    @Value("${spring.application.name}")
    String appName;

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String homePage() {
        return appName;
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<Product>> getProducts() {
        log.info("Getting products");
        List<Product> products = productService.retrieveProducts();
        if (products.isEmpty()) {
            log.info("There are no products");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/api/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable(name = "productId") Long productId) {
        log.info("Getting Product with id = [" + productId + "]");
        Product product = productService.getProductById(productId);
        if (product == null) {
            log.info("Product with id = [" + productId + "] not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping(value = "/api/products")
    public ResponseEntity<Void> addProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
        log.info("Saving Product = [" + product + "]");
        productService.addProduct(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/products/{id}").buildAndExpand(product.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "productId") Long productId) {
        log.info("Deleting Product with id = [" + productId + "]");
        Product product = productService.getProductById(productId);
        if (product == null) {
            log.info("Unable to delete product with id = [" + productId + "] because it's not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.deleteProductById(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/api/products/{productId}")
    public ResponseEntity<Void> updateProduct(@RequestBody Product product,
                                              @PathVariable(name = "productId") Long productId) {
        log.info("Updating Product with id = [" + productId + "]");
        Product prod = productService.getProductById(productId);
        if (prod == null) {
            log.info("Product with id = [" + productId + "] not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.updateProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
