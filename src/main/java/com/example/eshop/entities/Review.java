package com.example.eshop.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @NotNull
    @Min(1)
    @Max(5)
    private Double rating;

    @NotNull
    @Size(max = 200)
    private String comment;

    @ManyToOne
    private Product product;
}
