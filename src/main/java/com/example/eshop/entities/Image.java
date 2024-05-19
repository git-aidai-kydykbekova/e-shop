package com.example.eshop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String path;

    @OneToOne
    private Product product;

    @OneToMany
    private List<CartItem> items;

    @OneToMany
    private List<Order> orders;


}
