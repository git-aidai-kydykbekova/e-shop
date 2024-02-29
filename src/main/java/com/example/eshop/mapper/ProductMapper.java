package com.example.eshop.mapper;

import com.example.eshop.dto.product.ProductResponse;
import com.example.eshop.entities.Product;

import java.util.List;

public interface ProductMapper {
    List<ProductResponse> toDtoS(List<Product> all);

    List<ProductResponse> favoriteProducts(List<Product> products);

}
