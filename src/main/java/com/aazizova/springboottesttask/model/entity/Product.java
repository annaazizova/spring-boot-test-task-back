package com.aazizova.springboottesttask.model.entity;

import com.google.code.siren4j.annotations.Siren4JEntity;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity
@Data
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Siren4JEntity(entityClass = "product")
@FieldNameConstants
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String brand;

    private double price;

    private long quantity;

    public Product(String name, String brand, double price, long quantity) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
    }
}
