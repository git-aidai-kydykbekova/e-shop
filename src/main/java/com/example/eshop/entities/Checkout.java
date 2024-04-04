package com.example.eshop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Checkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String firstName;
    public String surname;
    public String companyName;
    public String country;
    public String city;
    public String province;
    public Integer zipCode;
    public Long number;
    public String email;
    private boolean verifyOrder = false;
    @ManyToOne
    private Customer customer;
}
