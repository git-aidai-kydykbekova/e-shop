package com.example.eshop.entities;

import com.example.eshop.role.Type;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String size;
    private String color;
    private String SKU;
    private Integer price;
    private String date;
    private boolean isExist = true;
//    @Enumerated(EnumType.STRING)
    private Type type;
    private Double rating;
    private Integer totalreview;

    @ManyToOne
    private Category category;

    @ManyToOne()
    private Customer customer;

    @OneToMany()
    private List<Review> productReview;

}
