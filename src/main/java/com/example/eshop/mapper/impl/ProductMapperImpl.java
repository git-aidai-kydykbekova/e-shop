package com.example.eshop.mapper.impl;

import com.example.eshop.dto.product.ProductResponse;
import com.example.eshop.entities.Product;
import com.example.eshop.mapper.ProductMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapperImpl implements ProductMapper {
//    private final ProductResponse productResponses;
    @Override
    public List<ProductResponse> toDtoS(List<Product> all) {
        List<ProductResponse> productResponses = new ArrayList<>();
        for(Product product: all) {
            productResponses.add(toDto(product));
        }
        return productResponses;
    }
    public ProductResponse toDto(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setName(product.getName());
        productResponse.setId(product.getId());
        productResponse.setPrice(product.getPrice());
        productResponse.setDate(productResponse.getDate());
        productResponse.setSize(product.getSize());
        productResponse.setSKU(product.getSKU());
        productResponse.setCategory(product.getCategory().getName());
        productResponse.setExist(product.isExist());
        productResponse.setDescription(product.getDescription());
        productResponse.setColor(productResponse.getColor());

        return productResponse;
    }
}
