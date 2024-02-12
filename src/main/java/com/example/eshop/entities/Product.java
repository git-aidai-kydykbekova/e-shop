package com.example.eshop.entities;

import com.example.eshop.Role.Type;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
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

    @ManyToOne
    private Category category;


}
