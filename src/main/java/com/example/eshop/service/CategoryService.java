package com.example.eshop.service;

import com.example.eshop.dto.category.CategoryResponse;

import java.util.List;

public interface CategoryService {
    void addCategory(String name, String token);

    List<CategoryResponse> getAll();
}
