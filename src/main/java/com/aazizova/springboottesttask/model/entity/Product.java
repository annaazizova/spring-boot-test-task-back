package com.aazizova.springboottesttask.model.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.code.siren4j.annotations.Siren4JEntity;
import com.google.code.siren4j.annotations.Siren4JLink;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.extern.log4j.Log4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Product.
 */
@Entity
@Data
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Siren4JEntity(entityClass = "product", uri = "/api/products/{id}",
        links = {
                @Siren4JLink(rel = "products", href = "/api/products")
        })
@FieldNameConstants
@Log4j
public class Product {
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Name.
     */
    private String name;

    /**
     * Brand.
     */
    private String brand;

    /**
     * Price.
     */
    private double price;

    /**
     * Quantity.
     */
    private long quantity;

    /**
     * To string.
     *
     * @return String
     */
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();

        String jsonString = "";
        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            jsonString = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.warn("Can't prettify json = {}", e);
        }
        return jsonString;
    }
}
