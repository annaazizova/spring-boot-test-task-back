package com.aazizova.springboottesttask.model.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Anna on 02.02.2019.
 */
@Entity
@Data
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
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
