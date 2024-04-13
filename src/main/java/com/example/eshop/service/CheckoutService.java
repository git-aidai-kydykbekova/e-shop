package com.example.eshop.service;

import com.example.eshop.dto.checkout.CheckoutRequest;

public interface CheckoutService {
    void checkout(CheckoutRequest checkoutRequest, String token);

    String verifyCode( String code, String token);
}
