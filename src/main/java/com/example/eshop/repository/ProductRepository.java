package com.example.eshop.repository;

import com.example.eshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long > {
    Optional<Object> findBySKU(String sku);
}
