package com.example.eshop.repository;

import com.example.eshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long > {
    Optional<Object> findBySKU(String sku);
}
