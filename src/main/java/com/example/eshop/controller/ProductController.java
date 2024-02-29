package com.example.eshop.controller;

import com.example.eshop.dto.Review.ReviewResponse;
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

    @GetMapping("/myproducts")
    public List<ProductResponse> getMyProducts(@RequestHeader ("Authorization") String token){
        return productService.getMyProducts(token);
    }
    @PostMapping("/buy/{productId}")
    public void buyProduct(@PathVariable Long productId,@RequestHeader ("Authorization") String token) {
        productService.buyProduct(productId, token);
    }
    @GetMapping("/user/basket")
    public List<ProductResponse> userBasket(@RequestHeader ("Authorization") String token) {
        return productService.getMyProducts(token);
    }
    @DeleteMapping("/delete/{productId}")
    public void delete(@PathVariable Long productId,@RequestHeader("Authorization") String token) {
        productService.deleteProduct(productId, token);
    }
    @PutMapping("/update/{productId}")
    public void updateById(@PathVariable Long productId, @RequestBody ProductRequest productRequest, @RequestHeader ("Authorization") String token){
        productService.updateById(productId, productRequest, token);
    }
    @PostMapping("/favorite/{productId}")
    public void addFavoriteProduct(@PathVariable Long productId, @RequestHeader ("Authorization") String token) {
        productService.addFavoriteProduct(productId, token);
    }
    @GetMapping("/list/favorite")
    public List<ProductResponse> favoriteProducts (@RequestHeader ("Authorization") String token) {
        return productService.getMyFavoriteProducts(token);
    }


    @GetMapping("/comments/{productId}")
    public List<ReviewResponse> comments(@PathVariable Long productId) {
        return productService.comments(productId);
    }
}