package com.aazizova.springboottesttask.controller;

import com.aazizova.springboottesttask.model.entity.Product;
import com.aazizova.springboottesttask.service.ProductService;
import com.aazizova.springboottesttask.utils.ProductUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Log4j2
//@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductUtils productUtils;

    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<List<Product>> getProducts() {
        log.info("Getting products");
        List<Product> products = productService.retrieveProducts();
        if (products.isEmpty()) {
            log.info("There are no products");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Product> getProduct(@PathVariable(name = "productId") Long productId) {
        log.info("Getting Product with id = [" + productId + "]");
        Product product = productService.getProductById(productId);
        if (product == null) {
            log.info("Product with id = [" + productId + "] not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/")
    @Secured("ROLE_ADMIN")
    public ResponseEntity addProduct(@RequestBody Product product) {
        log.info("Saving Product = [" + product + "]");
        productService.addProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}")
    @Secured("ROLE_ADMIN")
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

    @PutMapping("/{productId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> updateProduct(@RequestBody Product product,
                                              @PathVariable(name = "productId") Long productId) {
        log.info("Updating Product =[" + product + "]");
        Product prod = productService.getProductById(productId);
        if (prod == null) {
            log.info("Product with id = [" + productId + "] not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.updateProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/export")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Void> exportProducts(@RequestBody List<Product> products) {
        log.info("products = [" + products + "]");
        log.info("Exporting filtered products");
        if (!productUtils.exportToXLS(products)) {
            log.info("Can't export");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/leftovers")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<List<Product>> getLeftovers() {
        log.info("Getting leftovers");
        List<Product> leftovers = productService.retrieveLeftovers();
        if (leftovers.isEmpty()) {
            log.info("There are no leftovers");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(leftovers, HttpStatus.OK);
    }
}
