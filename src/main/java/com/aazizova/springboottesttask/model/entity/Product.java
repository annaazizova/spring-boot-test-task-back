package com.aazizova.springboottesttask.model.entity;

import com.google.code.siren4j.annotations.Siren4JEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

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
@Siren4JEntity(entityClass = "product", uri = "/api/products/{id}")
@FieldNameConstants
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
}
