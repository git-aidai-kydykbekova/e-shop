package com.example.eshop.service;

import com.example.eshop.dto.checkout.CheckoutRequest;

public interface CheckoutService {
    void checkout(CheckoutRequest checkoutRequest, String token);

    void verifyCode(Long checkId, String token, Long code);
}
