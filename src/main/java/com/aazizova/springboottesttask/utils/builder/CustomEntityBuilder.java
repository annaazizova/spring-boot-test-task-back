package com.aazizova.springboottesttask.utils.builder;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.google.code.siren4j.component.Entity;
import static com.google.code.siren4j.component.builder.EntityBuilder.createEntityBuilder;

@Component
public class CustomEntityBuilder {
    public Entity createErrorEntity(HttpStatus httpStatus, String message) { //TODO move entity creation to another class
        return createEntityBuilder()
                .setComponentClass("error")
                .addProperty("status", httpStatus)
                .addProperty("code", httpStatus.value())
                .addProperty("message", message)
                .build();
    }
}
