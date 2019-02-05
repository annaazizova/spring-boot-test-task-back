package com.aazizova.springboottesttask.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Anna on 02.02.2019.
 */
@Entity
@Data
@Table(name = "products")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    @NotNull
    private long id;

    private String name;

    private String brand;

    private double price;

    private String quantity;

    public Product(String name, String brand, double price, String quantity) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
    }
}
