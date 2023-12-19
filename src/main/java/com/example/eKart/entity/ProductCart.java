package com.example.eKart.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name ="productCart")
public class ProductCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cartId;
    private long productId;
    private String productName;
    private String category;
    private double price;
    private String color;

}
