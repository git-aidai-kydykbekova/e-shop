package com.example.eshop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private String code;
    @Column(name = "verification")
    private boolean isVerify;
    private LocalDateTime codeGeneratedTime;

    @ManyToOne
    private Customer customer;
}
