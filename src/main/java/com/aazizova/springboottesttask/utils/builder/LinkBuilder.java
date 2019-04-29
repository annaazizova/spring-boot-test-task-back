package com.aazizova.springboottesttask.utils.builder;

import com.aazizova.springboottesttask.model.entity.Product;
import com.google.code.siren4j.component.Link;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static com.google.code.siren4j.component.builder.LinkBuilder.createLinkBuilder;

@Component
public class LinkBuilder {
    private static final String SELF_REL = "self";

    public Link createProductLink(Product product, HttpServletRequest httpServletRequest) {
        return createResourceUri(product.getId(), httpServletRequest.getRequestURI());
    }

    private Link createResourceUri(long id, String requestURI) {
        return createLinkBuilder().setHref(requestURI.concat("/").concat(String.valueOf(id))).setRelationship(SELF_REL).build();
    }
}
