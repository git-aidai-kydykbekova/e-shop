package com.example.eshop.entities;

import com.example.eshop.role.Tag;
import com.example.eshop.role.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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

    private Type type;
    private Double rating;
    private Integer totalreview;

    @ManyToOne
    private Category category;

    @ManyToMany()
    private List<Customer> customer;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Comparison comparison;

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    @ElementCollection(targetClass = Tag.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "product_tags", joinColumns = @JoinColumn(name = "product_id"))
    @Enumerated(EnumType.STRING)
    private List<Tag> tags;
}
