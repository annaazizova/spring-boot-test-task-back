package com.aazizova.springboottesttask.controller;

import com.aazizova.springboottesttask.model.entity.Product;
import com.aazizova.springboottesttask.service.ProductService;
import com.aazizova.springboottesttask.utils.ProductUtils;
import com.aazizova.springboottesttask.utils.builder.CustomEntityBuilder;
import com.google.code.siren4j.component.Entity;
import com.google.code.siren4j.converter.ReflectingConverter;
import com.google.code.siren4j.error.Siren4JException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    CustomEntityBuilder customEntityBuilder;

    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Entity getProducts(HttpServletRequest request) throws Siren4JException {
        log.info("Getting products");
        List<Product> products = productService.retrieveProducts();
        if (products.isEmpty()) {
            log.info("There are no products");
            return customEntityBuilder.buildErrorEntity(HttpStatus.NO_CONTENT, "There are no products");
        }
        return customEntityBuilder.buildProductsEntity(products, request, "products");
    }

    @GetMapping("/{productId}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Entity getProduct(@PathVariable(name = "productId") Long productId) throws Siren4JException {//TODO add exception handling
        log.info("Getting Product with id = [" + productId + "]");
        Product product = productService.getProductById(productId);
        if (product == null) {
            log.info("Product with id = [" + productId + "] not found");
            return customEntityBuilder.buildErrorEntity(HttpStatus.NOT_FOUND, "Product with id = [" + productId + "] not found");
        }
        return ReflectingConverter.newInstance().toEntity(product);
    }

    @PostMapping("/")
    @Secured("ROLE_ADMIN")
    public Entity addProduct(@RequestBody Product product) throws Siren4JException {
        log.info("Saving Product = [" + product + "]");
        productService.addProduct(product);
        return ReflectingConverter.newInstance().toEntity(product);
    }

    @DeleteMapping("/{productId}")
    @Secured("ROLE_ADMIN")
    public Entity deleteProduct(@PathVariable(name = "productId") Long productId) {
        log.info("Deleting Product with id = [" + productId + "]");
        Product product = productService.getProductById(productId);
        if (product == null) {
            log.info("Unable to delete product with id = [" + productId + "] because it's not found");
            return customEntityBuilder.buildErrorEntity(HttpStatus.NOT_FOUND, "Unable to delete product with id = [" + productId + "] because it's not found");
        }
        productService.deleteProductById(productId);
        return customEntityBuilder.buildSuccessEntity();
    }

    @PutMapping("/{productId}")
    @Secured("ROLE_ADMIN")
    public Entity updateProduct(@RequestBody Product product,
                                @PathVariable(name = "productId") Long productId) {
        log.info("Updating Product =[" + product + "]");
        Product prod = productService.getProductById(productId);
        if (prod == null) {
            log.info("Product with id = [" + productId + "] not found");
            return customEntityBuilder.buildErrorEntity(HttpStatus.NOT_FOUND, "Product with id = [" + productId + "] not found");
        }
        productService.updateProduct(product);
        return customEntityBuilder.buildSuccessEntity();
    }

    @PostMapping("/export")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Entity exportProducts(@RequestBody List<Product> products) {
        log.info("products = [" + products + "]");
        log.info("Exporting filtered products");
        if (!productUtils.exportToXLS(products)) {
            log.info("Can't export");
            return customEntityBuilder.buildErrorEntity(HttpStatus.NO_CONTENT, "Can't export");
        }
        return customEntityBuilder.buildSuccessEntity();
    }

    @GetMapping("/leftovers")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Entity getLeftovers(HttpServletRequest request) {
        log.info("Getting leftovers");
        List<Product> leftovers = productService.retrieveLeftovers();
        if (leftovers.isEmpty()) {
            log.info("There are no leftovers");
            return customEntityBuilder.buildErrorEntity(HttpStatus.NO_CONTENT, "There are no leftovers");
        }
        return customEntityBuilder.buildProductsEntity(leftovers, request, "leftovers");
    }
}
