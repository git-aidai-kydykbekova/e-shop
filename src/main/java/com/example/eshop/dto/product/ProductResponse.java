package com.example.eshop.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResponse {
    private Long id;

    private String name;
    private Integer price;
    private String description;
    private String size;
    private String color;
    private String SKU;
    private String date;
    //private boolean isExist;
    private String category;
    private String type;
    private Double rating;

}