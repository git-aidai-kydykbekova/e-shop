package com.example.eshop.service;

import com.example.eshop.dto.product.ProductRequest;
import com.example.eshop.dto.product.ProductResponse;

import java.util.List;

public interface ProductService {
    void addProduct(ProductRequest productRequest, String token);

    List<ProductResponse> getAll(String token);

    void buyProduct(Long productId, String token);

    List<ProductResponse> getMyProducts(String token);
}
