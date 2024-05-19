package com.example.eshop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;

    @OneToOne(mappedBy = "customer")
    private User user;

    @ManyToMany
    private List<Product> products;

    @ManyToMany
    private List<Product> favoriteProducts;

    @OneToMany(mappedBy = "customer")
    private List<Checkout> checkouts;
    @OneToOne
    private Cart cart;
    @OneToMany
    private List<Order> orders;

}
