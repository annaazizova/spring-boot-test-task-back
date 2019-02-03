package com.aazizova.springboottesttask.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Anna on 02.02.2019.
 */
@Entity
@Table(name = "PRODUCTS")
@ToString
@EqualsAndHashCode
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    @Getter@Setter
    private long id;

    @Column(name = "NAME")
    @Getter@Setter
    private String name;

    @Column(name = "BRAND")
    @Getter@Setter
    private String brand;

    @Column(name = "PRICE")
    @Getter@Setter
    private double price;

    @Column(name = "QUANTITY")
    @Getter@Setter
    private String quantity;

    private Product(){}

    public Product(String name, String brand, double price, String quantity) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
    }
}
