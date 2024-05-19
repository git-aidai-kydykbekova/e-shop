package com.example.eshop.repository;

import com.example.eshop.entities.Cart;
import com.example.eshop.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findBySkuAndCart(String sku, Cart cart);
}
