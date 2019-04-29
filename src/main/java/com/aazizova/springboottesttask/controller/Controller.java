package com.aazizova.springboottesttask.controller;

import com.aazizova.springboottesttask.model.entity.Product;
import com.aazizova.springboottesttask.service.ProductService;
import com.aazizova.springboottesttask.utils.ProductUtils;
import com.google.code.siren4j.component.Entity;
import com.google.code.siren4j.component.builder.EntityBuilder;
import com.google.code.siren4j.converter.ReflectingConverter;
import com.google.code.siren4j.error.Siren4JException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

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
    public Entity getProducts() throws Siren4JException {

        log.info("Getting products");

        if(isUserHasAccess()) {
            List<Product> products = productService.retrieveProducts();
            if (products.isEmpty()) {
                log.info("There are no products");
                return createErrorEntity(HttpStatus.NO_CONTENT, "There are no products");
            }
            final List<Entity> productEntities = products.stream().map(this::buildProductEntity).collect(Collectors.toList());

            return EntityBuilder.newInstance()
                    .setComponentClass("products")
                    .addSubEntities(productEntities)
                    .build();
        }
        log.info("User has no access");
        return createErrorEntity(HttpStatus.FORBIDDEN, "User has no access");
    }

    @GetMapping("/api/products/{productId}")
    public Entity getProduct(@PathVariable(name = "productId") Long productId) throws Siren4JException {//TODO add exception handling
        log.info("Getting Product with id = [" + productId + "]");
        //if(isUserHasAccess()){
            Product product = productService.getProductById(productId);
            if (product == null) {
                log.info("Product with id = [" + productId + "] not found");
                return createErrorEntity(HttpStatus.NOT_FOUND, "Product with id = [" + productId + "] not found");
            }
            return ReflectingConverter.newInstance().toEntity(product);
        /*}
        log.info("User has no access");
        return createErrorEntity(HttpStatus.FORBIDDEN, "User has no access");*/
    }

    @PostMapping(value = "/api/products")
    public Entity addProduct(@RequestBody Product product) throws Siren4JException {
        log.info("Saving Product = [" + product + "]");
        if(isUserHasAccess()){
            productService.addProduct(product);
            return ReflectingConverter.newInstance().toEntity(product);
        }
        log.info("User has no access");
        return createErrorEntity(HttpStatus.FORBIDDEN, "User has no access");
    }

    @DeleteMapping("/api/products/{productId}")
    public Entity deleteProduct(@PathVariable(name = "productId") Long productId) {
        log.info("Deleting Product with id = [" + productId + "]");
        if(isUserHasAccess()){
            Product product = productService.getProductById(productId);
            if (product == null) {
                log.info("Unable to delete product with id = [" + productId + "] because it's not found");
                return createErrorEntity(HttpStatus.NOT_FOUND, "Unable to delete product with id = [" + productId + "] because it's not found");
            }
            productService.deleteProductById(productId);
            return null;//new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("User has no access");
        return createErrorEntity(HttpStatus.FORBIDDEN, "User has no access");
    }

    @PutMapping("/api/products/{productId}")
    public Entity updateProduct(@RequestBody Product product,
                                              @PathVariable(name = "productId") Long productId) {
        log.info("Updating Product =[" + product + "]");
        if(isUserHasAccess()){
            Product prod = productService.getProductById(productId);
            if (prod == null) {
                log.info("Product with id = [" + productId + "] not found");
                return createErrorEntity(HttpStatus.NOT_FOUND, "Product with id = [" + productId + "] not found");
            }
            productService.updateProduct(product);
            return null;//new ResponseEntity<>(HttpStatus.OK);
        }
        log.info("User has no access");
        return createErrorEntity(HttpStatus.FORBIDDEN, "User has no access");
    }

    @PostMapping("/api/products/export")
    public Entity exportProducts(@RequestBody List<Product> products) {
        log.info("products = [" + products + "]");
        log.info("Exporting filtered products");
        if(isUserHasAccess()){
            if (!productUtils.exportToXLS(products)) {
                log.info("Can't export");
                return createErrorEntity(HttpStatus.NO_CONTENT, "Can't export");
            }
            return null;//new ResponseEntity<>(HttpStatus.OK);
        }
        log.info("User has no access");
        return createErrorEntity(HttpStatus.FORBIDDEN, "User has no access");
    }

    @GetMapping("/api/products/leftovers")
    public Entity getLeftovers() {
        log.info("Getting products");
        if(isUserHasAccess()){
            List<Product> leftovers = productService.retrieveLeftovers();
            if (leftovers.isEmpty()) {
                log.info("There are no leftovers");
                return createErrorEntity(HttpStatus.NO_CONTENT, "There are no leftovers");
            }
            final List<Entity> leftoversEntities = leftovers.stream().map(this::buildProductEntity).collect(Collectors.toList());

            return EntityBuilder.newInstance()
                    .setComponentClass("leftovers")
                    .addSubEntities(leftoversEntities)
                    .build();
        }
        log.info("User has no access");
        return createErrorEntity(HttpStatus.FORBIDDEN, "User has no access");
    }

    private Entity createErrorEntity(HttpStatus httpStatus, String message) {
        return EntityBuilder.newInstance()
                .setComponentClass("error")
                .addProperty("status", httpStatus)
                .addProperty("code", httpStatus.value())
                .addProperty("message", message)
                .build();
    }

    private Entity buildProductEntity(Product product) {
        return EntityBuilder.newInstance().setRelationship("product")
                .addProperty(Product.FIELD_ID, product.getId())
                .addProperty(Product.FIELD_NAME, product.getName())
                .addProperty(Product.FIELD_BRAND, product.getBrand())
                .addProperty(Product.FIELD_PRICE, product.getPrice())
                .addProperty(Product.FIELD_QUANTITY, product.getQuantity())//TODO add link
                .build();
    }

    private boolean isUserHasAccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));//"ROLE_ADMIN"
    }
}
