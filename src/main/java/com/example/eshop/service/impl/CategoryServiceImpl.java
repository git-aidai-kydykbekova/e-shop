package com.example.eshop.service.impl;

import com.example.eshop.dto.category.CategoryResponse;
import com.example.eshop.entities.Category;
import com.example.eshop.exception.NotFoundException;
import com.example.eshop.mapper.CategoryMapper;
import com.example.eshop.repository.CategoryRepository;
import com.example.eshop.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public void addCategory(String name, String token) {
        if(categoryRepository.findByName(name).isPresent()) {
            throw new NotFoundException("Category with name: " + name+ " is already exist!", HttpStatus.BAD_REQUEST);
        }
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryResponse> getAll() {
        return categoryMapper.toDtos(categoryRepository.findAll());
    }
}
