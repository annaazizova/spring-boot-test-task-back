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

@Component
public class CustomActionBuilder {
    public Action buildAction(String name, String title, ActionImpl.Method method, String additionalUri) {
        return ActionBuilder.newInstance()
                .setName(name)
                .setTitle(title)
                .setMethod(method)
                .setHref("/api/products".concat(additionalUri))
                .setType(MediaType.APPLICATION_JSON.getType())
                .addField(FieldBuilder.newInstance().setName(Product.FIELD_NAME).setType(FieldType.TEXT).build())
                .addField(FieldBuilder.newInstance().setName(Product.FIELD_BRAND).setType(FieldType.TEXT).build())
                .addField(FieldBuilder.newInstance().setName(Product.FIELD_PRICE).setType(FieldType.NUMBER).build())
                .addField(FieldBuilder.newInstance().setName(Product.FIELD_QUANTITY).setType(FieldType.NUMBER).build())
                .build();
    }

    public Action buildNoFieldsAction(String name, String title, ActionImpl.Method method, String additionalUri) {
        return ActionBuilder.newInstance()
                .setName(name)
                .setTitle(title)
                .setMethod(method)
                .setHref("/api/products".concat(additionalUri))
                .setType(MediaType.APPLICATION_JSON.getType())
                .build();
    }

    public List<Action> buildActions() {
        List<Action> actions = new ArrayList<>();
        actions.add(buildAction("add-product", "Create new product", ActionImpl.Method.POST, ""));
        actions.add(buildNoFieldsAction("delete-product", "Delete existing product", ActionImpl.Method.DELETE, "/{productId}"));
        actions.add(buildAction("update-product", "Update existing product", ActionImpl.Method.PUT, "/{productId}"));
        actions.add(buildNoFieldsAction("export-products", "Export all product to xls file", ActionImpl.Method.GET, "/export"));
        return actions;
    }
}
