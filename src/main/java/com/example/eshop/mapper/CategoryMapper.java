package com.example.eshop.mapper;

import com.example.eshop.dto.category.CategoryResponse;
import com.example.eshop.entities.Category;


import java.util.List;

public interface CategoryMapper {
    List<CategoryResponse> toDtos(List<Category> all);
}
