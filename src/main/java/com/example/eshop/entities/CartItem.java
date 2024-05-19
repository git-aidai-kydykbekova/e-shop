package com.example.eshop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table( name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer price;
    private Integer quantity;
    private Integer subtotal;
    private String sku;

    @ManyToOne(cascade = CascadeType.ALL)
    private Image image;

    @ManyToOne
    private Cart cart;


}
