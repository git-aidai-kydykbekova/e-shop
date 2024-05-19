package com.example.eshop.mapper;

import com.example.eshop.dto.cart.CartResponse;
import com.example.eshop.entities.Cart;

public interface CartMapper {
    CartResponse toDto(Cart cart);
}
