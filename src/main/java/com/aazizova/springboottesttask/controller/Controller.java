package com.aazizova.springboottesttask.controller;

import com.aazizova.springboottesttask.model.entity.Product;
import com.aazizova.springboottesttask.service.ProductService;
import com.aazizova.springboottesttask.utils.ProductUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by Anna on 02.02.2019.
 */
@RestController
@Log4j2
//@CrossOrigin(origins = "http://localhost:3000")
public class Controller {

    @Autowired
    ProductService productService;

    @Autowired
    ProductUtils productUtils;

    @RequestMapping(value = "/user")
    public Principal user(Principal principal){
        return principal;
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<Product>> getProducts() {
        log.info("Getting products");
        if(isUserHasAccess()){
            List<Product> products = productService.retrieveProducts();
            if (products.isEmpty()) {
                log.info("There are no products");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        log.info("User has no access");
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    private boolean isUserHasAccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));//"ROLE_ADMIN"
    }

    @GetMapping("/api/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable(name = "productId") Long productId) {
        log.info("Getting Product with id = [" + productId + "]");
        if(isUserHasAccess()){
            Product product = productService.getProductById(productId);
            if (product == null) {
                log.info("Product with id = [" + productId + "] not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        log.info("User has no access");
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping(value = "/api/products")
    public ResponseEntity addProduct(@RequestBody Product product) {
        log.info("Saving Product = [" + product + "]");
        if(isUserHasAccess()){
            productService.addProduct(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        }
        log.info("User has no access");
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/api/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "productId") Long productId) {
        log.info("Deleting Product with id = [" + productId + "]");
        if(isUserHasAccess()){
            Product product = productService.getProductById(productId);
            if (product == null) {
                log.info("Unable to delete product with id = [" + productId + "] because it's not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            productService.deleteProductById(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("User has no access");
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping("/api/products/{productId}")
    public ResponseEntity<Void> updateProduct(@RequestBody Product product,
                                              @PathVariable(name = "productId") Long productId) {
        log.info("Updating Product =[" + product + "]");
        if(isUserHasAccess()){
            Product prod = productService.getProductById(productId);
            if (prod == null) {
                log.info("Product with id = [" + productId + "] not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            productService.updateProduct(product);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        log.info("User has no access");
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/api/products/export")
    public ResponseEntity<Void> exportProducts(@RequestBody List<Product> products) {
        log.info("products = [" + products + "]");
        log.info("Exporting filtered products");
        if(isUserHasAccess()){
            if (!productUtils.exportToXLS(products)) {
                log.info("Can't export");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        log.info("User has no access");
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/api/products/leftovers")
    public ResponseEntity<List<Product>> getLeftovers() {
        log.info("Getting products");
        if(isUserHasAccess()){
            List<Product> leftovers = productService.retrieveLeftovers();
            if (leftovers.isEmpty()) {
                log.info("There are no leftovers");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(leftovers, HttpStatus.OK);
        }
        log.info("User has no access");
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
