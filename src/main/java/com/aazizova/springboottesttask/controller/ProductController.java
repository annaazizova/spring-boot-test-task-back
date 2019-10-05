package com.aazizova.springboottesttask.controller;

import com.aazizova.springboottesttask.model.entity.Product;
import com.aazizova.springboottesttask.service.ProductService;
import com.aazizova.springboottesttask.utils.ProductUtils;
import com.aazizova.springboottesttask.utils.builder.CustomEntityBuilder;
import com.google.code.siren4j.component.Entity;
import com.google.code.siren4j.converter.ReflectingConverter;
import com.google.code.siren4j.error.Siren4JException;
import io.swagger.annotations.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Product controller.
 */
@RestController
@RequestMapping("/api/products")
@Log4j2
//@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "Simple Inventory System",
        description = "Operations for products in Simple Inventory System")
public class ProductController {
    /**
     * Product service.
     */
    @Autowired
    private ProductService productService;

    /**
     * Product utils.
     */
    @Autowired
    private ProductUtils productUtils;

    /**
     * Custom entity builder.
     */
    @Autowired
    private CustomEntityBuilder customEntityBuilder;

    /**
     * Returns entity of all products.
     *
     * @param req HttpServletRequest
     * @return Entity
     * @throws Siren4JException if something with siren format happened
     */
    @ApiOperation(value = "View all products",
                    response = Entity.class,
                    authorizations = {@Authorization(value = "securitySchemaOAuth2")}
    )
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK,
                    message = "Successfully retrieved products"),
            @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT,
                    message = "There are no products"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED,
                    message = "You are not authorized to view the resource"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN,
                    message = "Access is forbidden")
    })
    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Entity products(final HttpServletRequest req)
            throws Siren4JException {
        log.info("Getting products");
        List<Product> products = productService.products();
        if (products.isEmpty()) {
            log.info("There are no products");
            return customEntityBuilder.errorEntity(HttpStatus.NO_CONTENT,
                    "There are no products");
        }
        return customEntityBuilder.productsEntity(products, req, "products");
    }

    /**
     * Returns entity of product.
     *
     * @param id id of product
     * @return Entity
     * @throws Siren4JException if something with siren format happened
     */
    @ApiOperation(value = "Get product by id",
            response = Entity.class,
            authorizations = {@Authorization(value = "securitySchemaOAuth2")}
    )
    @GetMapping("/{productId}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Entity product(final @PathVariable(name = "id") Long id)
            throws Siren4JException { //TODO add exception handling
        log.info("Getting Product with id = [" + id + "]");
        Product product = productService.productWithId(id);
        if (product == null) {
            log.info("Product with id = [" + id + "] not found");
            return customEntityBuilder.errorEntity(HttpStatus.NOT_FOUND,
                    "Product with id = [" + id + "] not found");
        }
        return ReflectingConverter.newInstance().toEntity(product);
    }

    /**
     * Add product.
     *
     * @param product Product
     * @return Entity
     * @throws Siren4JException Siren4JException
     */
    @ApiOperation(value = "Add new product",
            response = Entity.class,
            authorizations = {@Authorization(value = "securitySchemaOAuth2")}
    )
    @PostMapping("/")
    @Secured("ROLE_ADMIN")
    public Entity addProduct(final @RequestBody Product product)
            throws Siren4JException {
        log.info("Saving Product = [" + product + "]");
        productService.addProduct(product);
        return ReflectingConverter.newInstance().toEntity(product);
    }

    /**
     * Returns entity of deleted product.
     *
     * @param id id of product
     * @return Entity
     */
    @ApiOperation(value = "Delete product",
            response = Entity.class,
            authorizations = {@Authorization(value = "securitySchemaOAuth2")}
    )
    @DeleteMapping("/{productId}")
    @Secured("ROLE_ADMIN")
    public Entity deleteProduct(final @PathVariable(name = "id") Long id) {
        log.info("Deleting Product with id = [" + id + "]");
        Product product = productService.productWithId(id);
        if (product == null) {
            log.info("Unable to delete product with id = [" + id + "]"
                    + " because it's not found");
            return customEntityBuilder.errorEntity(HttpStatus.NOT_FOUND,
                    "Unable to delete product with id = [" + id + "]"
                            + " because it's not found");
        }
        productService.deleteProductById(id);
        return customEntityBuilder.successEntity();
    }

    /**
     * Returns entity of updated product.
     *
     * @param product product
     * @param id      id of product
     * @return Entity
     */
    @ApiOperation(value = "Update product",
            response = Entity.class,
            authorizations = {@Authorization(value = "securitySchemaOAuth2")}
    )
    @PutMapping("/{productId}")
    @Secured("ROLE_ADMIN")
    public Entity updateProduct(final @RequestBody Product product,
                                final @PathVariable(name = "id") Long id) {
        log.info("Updating Product =[" + product + "]");
        Product prod = productService.productWithId(id);
        if (prod == null) {
            log.info("Product with id = [" + id + "] not found");
            return customEntityBuilder.errorEntity(HttpStatus.NOT_FOUND,
                    "Product with id = [" + id + "] not found");
        }
        productService.updateProduct(product);
        return customEntityBuilder.successEntity();
    }

    /**
     * Export products.
     *
     * @param products List<Product>
     * @return Entity
     */
    @ApiOperation(value = "Export products",
            response = Entity.class,
            authorizations = {@Authorization(value = "securitySchemaOAuth2")}
    )
    @GetMapping("/export")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Entity exportProducts(final @RequestBody List<Product> products) {
        log.info("products = [" + products + "]");
        log.info("Exporting filtered products");
        if (!productUtils.exportToXLS(products)) {
            log.info("Can't export");
            return customEntityBuilder.errorEntity(HttpStatus.NO_CONTENT,
                    "Can't export");
        }
        return customEntityBuilder.successEntity();
    }

    /**
     * Leftovers.
     *
     * @param request HttpServletRequest
     * @return Entity
     */
    @ApiOperation(value = "Get leftovers",
            response = Entity.class,
            authorizations = {@Authorization(value = "securitySchemaOAuth2")}
    )
    @GetMapping("/leftovers")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Entity leftovers(final HttpServletRequest request) {
        log.info("Getting leftovers");
        List<Product> leftovers = productService.leftovers();
        if (leftovers.isEmpty()) {
            log.info("There are no leftovers");
            return customEntityBuilder.errorEntity(HttpStatus.NO_CONTENT,
                    "There are no leftovers");
        }
        return customEntityBuilder.productsEntity(leftovers,
                request, "leftovers");
    }
}
