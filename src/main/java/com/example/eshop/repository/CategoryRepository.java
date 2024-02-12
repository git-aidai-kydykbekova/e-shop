package com.example.eshop.repository;

import com.example.eshop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long>  {
    Optional<Category> findByName(String category);
}
