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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@Log4j2
//@CrossOrigin(origins = "http://localhost:3000")
public class Controller {

    @Autowired
    ProductService productService;

    @Autowired
    ProductUtils productUtils;

    @Autowired
    CustomEntityBuilder customEntityBuilder;

    @RequestMapping(value = "/user")
    public Principal user(Principal principal){
        return principal;
    }

    @GetMapping("/api/products")
    public Entity getProducts(HttpServletRequest request) throws Siren4JException {
        log.info("Getting products");
        if(isUserHasAccess()) {
            List<Product> products = productService.retrieveProducts();
            if (products.isEmpty()) {
                log.info("There are no products");
                return customEntityBuilder.buildErrorEntity(HttpStatus.NO_CONTENT, "There are no products");
            }

            return customEntityBuilder.buildProductsEntity(products, request, "products");
        }
        log.info("User has no access");
        return customEntityBuilder.buildErrorEntity(HttpStatus.FORBIDDEN, "User has no access");
    }

    @GetMapping("/api/products/{productId}")
    public Entity getProduct(@PathVariable(name = "productId") Long productId) throws Siren4JException {//TODO add exception handling
        log.info("Getting Product with id = [" + productId + "]");
        if(isUserHasAccess()){
            Product product = productService.getProductById(productId);
            if (product == null) {
                log.info("Product with id = [" + productId + "] not found");
                return customEntityBuilder.buildErrorEntity(HttpStatus.NOT_FOUND, "Product with id = [" + productId + "] not found");
            }
            return ReflectingConverter.newInstance().toEntity(product);
        }
        log.info("User has no access");
        return customEntityBuilder.buildErrorEntity(HttpStatus.FORBIDDEN, "User has no access");
    }

    @PostMapping(value = "/api/products")
    public Entity addProduct(@RequestBody Product product) throws Siren4JException {
        log.info("Saving Product = [" + product + "]");
        if(isUserHasAccess()){
            productService.addProduct(product);
            return ReflectingConverter.newInstance().toEntity(product);
        }
        log.info("User has no access");
        return customEntityBuilder.buildErrorEntity(HttpStatus.FORBIDDEN, "User has no access");
    }

    @DeleteMapping("/api/products/{productId}")
    public Entity deleteProduct(@PathVariable(name = "productId") Long productId) {
        log.info("Deleting Product with id = [" + productId + "]");
        if(isUserHasAccess()){
            Product product = productService.getProductById(productId);
            if (product == null) {
                log.info("Unable to delete product with id = [" + productId + "] because it's not found");
                return customEntityBuilder.buildErrorEntity(HttpStatus.NOT_FOUND, "Unable to delete product with id = [" + productId + "] because it's not found");
            }
            productService.deleteProductById(productId);
            return customEntityBuilder.buildSuccessEntity();
        }
        log.info("User has no access");
        return customEntityBuilder.buildErrorEntity(HttpStatus.FORBIDDEN, "User has no access");
    }

    @PutMapping("/api/products/{productId}")
    public Entity updateProduct(@RequestBody Product product,
                                              @PathVariable(name = "productId") Long productId) {
        log.info("Updating Product =[" + product + "]");
        if(isUserHasAccess()){
            Product prod = productService.getProductById(productId);
            if (prod == null) {
                log.info("Product with id = [" + productId + "] not found");
                return customEntityBuilder.buildErrorEntity(HttpStatus.NOT_FOUND, "Product with id = [" + productId + "] not found");
            }
            productService.updateProduct(product);
            return customEntityBuilder.buildSuccessEntity();
        }
        log.info("User has no access");
        return customEntityBuilder.buildErrorEntity(HttpStatus.FORBIDDEN, "User has no access");
    }

    @PostMapping("/api/products/export")
    public Entity exportProducts(@RequestBody List<Product> products) {
        log.info("products = [" + products + "]");
        log.info("Exporting filtered products");
        if(isUserHasAccess()){
            if (!productUtils.exportToXLS(products)) {
                log.info("Can't export");
                return customEntityBuilder.buildErrorEntity(HttpStatus.NO_CONTENT, "Can't export");
            }
            return customEntityBuilder.buildSuccessEntity();
        }
        log.info("User has no access");
        return customEntityBuilder.buildErrorEntity(HttpStatus.FORBIDDEN, "User has no access");
    }

    @GetMapping("/api/products/leftovers")
    public Entity getLeftovers(HttpServletRequest request) {
        log.info("Getting products");
        if(isUserHasAccess()){
            List<Product> leftovers = productService.retrieveLeftovers();
            if (leftovers.isEmpty()) {
                log.info("There are no leftovers");
                return customEntityBuilder.buildErrorEntity(HttpStatus.NO_CONTENT, "There are no leftovers");
            }
            return customEntityBuilder.buildProductsEntity(leftovers, request, "leftovers");
        }
        log.info("User has no access");
        return customEntityBuilder.buildErrorEntity(HttpStatus.FORBIDDEN, "User has no access");
    }

    private boolean isUserHasAccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));//"ROLE_ADMIN"
    }
}
