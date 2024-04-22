package com.example.eshop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "table_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createDate;
    private String title;
    private Integer price;
    private Integer total;
    private Integer quantity;
    private String sku;

    @ManyToOne
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Image image;
}
