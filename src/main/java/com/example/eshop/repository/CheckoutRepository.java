package com.example.eshop.repository;

import com.example.eshop.entities.Checkout;
import com.example.eshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
}
