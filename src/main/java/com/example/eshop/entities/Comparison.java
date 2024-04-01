package com.example.eshop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table
public class Comparison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String sales_package;
    private String model_number;
    private String secondary_material;
    private String configuration;
    private String upholstery_material;
    private String upholstery_color;

    private String filling_material;
    private String finish_type;
    private String adjustable_headrest;
    private Double max_load_capacity;
    private String origin_of_manufacture;

    private Double width;
    private Double height;
    private Double depth;
    private Double weight;
    private Double seat_height;
    private Double led_height;

    private String warranty_summary;
    private String warranty_service_type;
    private String covered_in_warranty;
    private String not_covered_in_warranty;
    private String domestic_warranty;

    @OneToOne
    private Product product;
}
