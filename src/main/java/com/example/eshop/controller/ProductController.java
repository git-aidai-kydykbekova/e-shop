package com.example.eshop.controller;

import com.example.eshop.dto.product.ProductRequest;
import com.example.eshop.dto.product.ProductResponse;
import com.example.eshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/furniture")
public class ProductController {
    private ProductService productService;
    @PostMapping("/add")
    public void add(@RequestBody ProductRequest productRequest, @RequestHeader("Authorization") String token) {
        productService.addProduct(productRequest, token);
    }

    @GetMapping("/list")
    public List<ProductResponse> productResponses(@RequestHeader ("Authorization") String token) {
        return productService.getAll(token);
    }
    @PostMapping("/buy/{productId}")
    public void buyProduct(@PathVariable Long productId,@RequestHeader ("Authorization") String token) {
        productService.buyProduct(productId, token);
    }
}
