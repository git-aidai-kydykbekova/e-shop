package com.example.eshop.controller;

import com.example.eshop.dto.category.CategoryResponse;
import com.example.eshop.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/category")
@RestController
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("/add")
    public void addCategory(@RequestParam String name, @RequestHeader("Authorization") String token) {
        categoryService.addCategory(name, token);
    }

    @GetMapping("/list")
    public List<CategoryResponse> categories() {
        return categoryService.getAll();
    }


}
