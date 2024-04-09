package com.example.eshop.controller;

import com.example.eshop.dto.Comparison.CompareRequest;
import com.example.eshop.dto.Comparison.CompareResponse;
import com.example.eshop.dto.Review.ReviewResponse;
import com.example.eshop.dto.product.ProductRequest;
import com.example.eshop.dto.product.ProductResponse;
import com.example.eshop.mapper.ComparisonMapper;
import com.example.eshop.repository.ComparisonRepository;
import com.example.eshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/furniture")
public class ProductController {
    private ProductService productService;
    private ComparisonMapper comparisonMapper;
    private ComparisonRepository comparisonRepository;

    @PostMapping("/add")
    public void add(@RequestBody ProductRequest productRequest, @RequestHeader("Authorization") String token) {
        productService.addProduct(productRequest, token);
    }

    @GetMapping("/list")
    public List<ProductResponse> productResponses(@RequestHeader ("Authorization") String token) {
        return productService.getAll(token);
    }

    @GetMapping("/myProducts")
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

    @PostMapping("/information/add")
    public void productcompare(@RequestBody CompareRequest compareRequest, @RequestHeader ("Authorization") String token) {
        productService.productcomparison(compareRequest, token);
    }
    @GetMapping("/compare1/{productId}")
    public ResponseEntity<CompareResponse> compare1(@PathVariable("productId")Long productId){
        return new ResponseEntity<>(comparisonMapper.modelTODto(comparisonRepository.findById(productId).get()), HttpStatus.OK);
    }
    @GetMapping("/compare2/{productId}")
    public ResponseEntity<CompareResponse> compare2(@PathVariable("productId")Long productId){
        return new ResponseEntity<>(comparisonMapper.modelTODto(comparisonRepository.findById(productId).get()), HttpStatus.OK);
    }

    @GetMapping("/sorting")
    public List<ProductResponse> getSortedProducts(@RequestParam String sortBy, @RequestParam String order) {
        return productService.getSortedProducts(sortBy, order);
    }
}