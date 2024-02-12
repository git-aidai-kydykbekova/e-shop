package com.example.eshop.mapper.impl;

import com.example.eshop.dto.category.CategoryResponse;
import com.example.eshop.entities.Category;
import com.example.eshop.mapper.CategoryMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public List<CategoryResponse> toDtos(List<Category> all) {
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for(Category category : all) {
            categoryResponses.add(toDto(category));
        }
        return categoryResponses;
    }

    private CategoryResponse toDto(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        return categoryResponse;

    }
}
