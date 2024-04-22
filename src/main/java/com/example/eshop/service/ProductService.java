package com.example.eshop.service;

import com.example.eshop.dto.AddToCartRequest;
import com.example.eshop.dto.Comparison.CompareRequest;
import com.example.eshop.dto.Review.ReviewResponse;
import com.example.eshop.dto.product.ProductRequest;
import com.example.eshop.dto.product.ProductResponse;
import com.example.eshop.entities.Comparison;
import com.example.eshop.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getSortedProducts(String sortBy, String order);
    void addProduct(ProductRequest productRequest, String token);

    List<ProductResponse> getAll(String token);

    List<ReviewResponse> comments(Long productId);

    void buyProduct(Long productId, String token);

    List<ProductResponse> getMyProducts(String token);

    void deleteProduct(Long productId, String token);

    void updateById(Long productId, ProductRequest productRequest, String token);

    Product getProductById(Long productId);

    void addFavoriteProduct(Long productId, String token);

    List<ProductResponse> getMyFavoriteProducts(String token);

    void productcomparison(CompareRequest compareRequest, String token);

    Comparison compareproducts(Long productId);

    Comparison compareproducts2(Long productId);

    void uploadFile(String token, MultipartFile file, Long productId);

    void deleteFavoriteProduct(Long productId, String token);

    void add(AddToCartRequest
            request, String token);
}
