package com.aazizova.springboottesttask.utils.builder;

import com.aazizova.springboottesttask.model.entity.Product;
import com.google.code.siren4j.component.Entity;
import com.google.code.siren4j.component.Link;
import com.google.code.siren4j.component.builder.EntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.code.siren4j.component.builder.EntityBuilder.createEntityBuilder;

/**
 * Custom entity builder.
 */
@Component
public class CustomEntityBuilder {
    /**
     * Custom link builder.
     */
    @Autowired
    private CustomLinkBuilder customLinkBuilder;

    /**
     * Custom action builder.
     */
    @Autowired
    private CustomActionBuilder customActionBuilder;

    /**
     * Builds error entity.
     *
     * @param httpStatus HttpStatus
     * @param msg        String
     * @return Entity
     */
    public Entity errorEntity(final HttpStatus httpStatus, final String msg) {
        return createEntityBuilder()
                .setComponentClass("error")
                .addProperty("status", httpStatus)
                .addProperty("code", httpStatus.value())
                .addProperty("message", msg)
                .build();
    }

    /**
     * Builds success entity.
     *
     * @return Entity
     */
    public Entity successEntity() {
        return createEntityBuilder()
                .setComponentClass("response")
                .addProperty("status", HttpStatus.OK)
                .addProperty("code", HttpStatus.OK.value())
                .addProperty("message", "Success")
                .build();
    }

    /**
     * Builds product entity.
     *
     * @param product      Product
     * @param req          HttpServletRequest
     * @param productsLink Link
     * @return Entity
     */
    private Entity productEntity(final Product product,
                                 final HttpServletRequest req,
                                 final Link productsLink) {
        return EntityBuilder.newInstance().setRelationship("product")
                .addProperty(Product.FIELD_ID, product.getId())
                .addProperty(Product.FIELD_NAME, product.getName())
                .addProperty(Product.FIELD_BRAND, product.getBrand())
                .addProperty(Product.FIELD_PRICE, product.getPrice())
                .addProperty(Product.FIELD_QUANTITY, product.getQuantity())
                .addLink(customLinkBuilder.productLink(product, req))
                .addLink(productsLink)
                .build();
    }

    /**
     * Builds products entity.
     *
     * @param products List<Product>
     * @param request  HttpServletRequest
     * @param type     String
     * @return Entity
     */
    public final Entity productsEntity(final List<Product> products,
                                       final HttpServletRequest request,
                                       final String type) {
        final Link productsLink = customLinkBuilder.productListLink(request);
        final List<Entity> productsEntities = products
                .stream()
                .map(product -> productEntity(product,
                        request,
                        productsLink))
                .collect(Collectors.toList());
        return EntityBuilder.newInstance()
                .setComponentClass(type)
                .addSubEntities(productsEntities)
                .addActions(customActionBuilder.actions())
                .build();
    }
}
