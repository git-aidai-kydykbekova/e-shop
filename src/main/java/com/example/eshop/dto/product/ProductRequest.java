package com.example.eshop.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;

@Getter
@Setter
public class ProductRequest {

    private String name;
    private Integer price;
    private String description;
    private String size;
    private String color;
    private String SKU;
    private String category;
    private String type;
    private Double rating;
}