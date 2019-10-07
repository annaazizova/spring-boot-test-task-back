package com.aazizova.springboottesttask.utils.builder;

import com.aazizova.springboottesttask.model.entity.Product;
import com.google.code.siren4j.component.Action;
import com.google.code.siren4j.component.builder.ActionBuilder;
import com.google.code.siren4j.component.builder.FieldBuilder;
import com.google.code.siren4j.component.impl.ActionImpl;
import com.google.code.siren4j.meta.FieldType;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom action builder.
 */
@Component
public final class CustomActionBuilder {
    /**
     * Action.
     *
     * @param name          String
     * @param title         String
     * @param method        ActionImpl.Method
     * @param additionalUri String
     * @return Action
     */
    private Action action(final String name,
                          final String title,
                          final ActionImpl.Method method,
                          final String additionalUri) {
        return ActionBuilder.newInstance()
                .setName(name)
                .setTitle(title)
                .setMethod(method)
                .setHref("/api/products".concat(additionalUri))
                .setType(MediaType.APPLICATION_JSON.getType())
                .addField(FieldBuilder
                        .newInstance()
                        .setName(Product.FIELD_NAME)
                        .setType(FieldType.TEXT)
                        .build())
                .addField(FieldBuilder
                        .newInstance()
                        .setName(Product.FIELD_BRAND)
                        .setType(FieldType.TEXT)
                        .build())
                .addField(FieldBuilder
                        .newInstance()
                        .setName(Product.FIELD_PRICE)
                        .setType(FieldType.NUMBER)
                        .build())
                .addField(FieldBuilder
                        .newInstance()
                        .setName(Product.FIELD_QUANTITY)
                        .setType(FieldType.NUMBER)
                        .build())
                .build();
    }

    /**
     * No field action.
     *
     * @param name          String
     * @param title         String
     * @param method        ActionImpl.Method
     * @param additionalUri String
     * @return Action
     */
    private Action noFieldsAction(final String name,
                                  final String title,
                                  final ActionImpl.Method method,
                                  final String additionalUri) {
        return ActionBuilder.newInstance()
                .setName(name)
                .setTitle(title)
                .setMethod(method)
                .setHref("/api/products".concat(additionalUri))
                .setType(MediaType.APPLICATION_JSON.getType())
                .build();
    }

    /**
     * No field action.
     *
     * @return List<Action>
     */
    public List<Action> actions() {
        List<Action> actions = new ArrayList<>();
        actions.add(action("add-product",
                "Create new product",
                ActionImpl.Method.POST,
                ""));
        actions.add(noFieldsAction("delete-product",
                "Delete existing product",
                ActionImpl.Method.DELETE,
                "/{productId}"));
        actions.add(action("update-product",
                "Update existing product",
                ActionImpl.Method.PUT,
                "/{productId}"));
        actions.add(noFieldsAction("export-products",
                "Export all product to xlsx file",
                ActionImpl.Method.GET,
                "/export/{productIds}"));
        return actions;
    }
}
