package com.example.eshop.dto.checkout;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CheckoutRequest {

    public String firstName;
    public String surname;
    public String companyName;
    public String country;
    public String city;
    public String province;
    public Integer zipCode;
    public Long number;
    public String email;
}
