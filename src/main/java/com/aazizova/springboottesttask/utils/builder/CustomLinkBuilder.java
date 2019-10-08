package com.aazizova.springboottesttask.utils.builder;

import com.aazizova.springboottesttask.model.entity.Product;
import com.google.code.siren4j.component.Link;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static com.google.code.siren4j.component.builder.LinkBuilder.createLinkBuilder;

/**
 * Custom link builder.
 */
@Component
public class CustomLinkBuilder {
    /**
     * Self rel.
     */
    private static final String SELF_REL = "self";

    /**
     * Products rel.
     */
    private static final String PRODUCTS_REL = "products";

    /**
     * Product link.
     *
     * @param product Product
     * @param req     HttpServletRequest
     * @return Link
     */
    public Link productLink(final Product product,
                            final HttpServletRequest req) {
        return resourceUri(product.getId(), req.getRequestURI());
    }

    /**
     * Resource link.
     *
     * @param id         Long
     * @param requestURI String
     * @return Link
     */
    private Link resourceUri(final Long id, final String requestURI) {
        return createLinkBuilder()
                .setHref(checkTrailingSlash(requestURI)
                        .concat(String.valueOf(id)))
                .setRelationship(SELF_REL).build();
    }

    /**
     * Check trailing slash.
     *
     * @param requestURI String
     * @return String
     */
    private String checkTrailingSlash(final String requestURI) {
        if (!requestURI.endsWith("/")) {
            return requestURI.concat("/");
        }
        return requestURI;
    }

    /**
     * Product list link.
     *
     * @param req HttpServletRequest
     * @return Link
     */
    public Link productListLink(final HttpServletRequest req) {
        return productsResourceUri(req.getRequestURI());
    }

    /**
     * Products resource uri.
     *
     * @param requestURI String
     * @return Link
     */
    private Link productsResourceUri(final String requestURI) {
        return createLinkBuilder()
                .setHref(requestURI)
                .setRelationship(PRODUCTS_REL).build();
    }
}
