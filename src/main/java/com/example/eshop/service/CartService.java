package com.example.eshop.service;

import com.example.eshop.dto.cart.AddToCartRequest;
import com.example.eshop.dto.cart.CartResponse;

public interface CartService {

    void addToCart(Long productId, String token, AddToCartRequest addToCartRequest);
}
