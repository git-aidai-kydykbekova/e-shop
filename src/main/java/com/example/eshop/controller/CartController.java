package com.example.eshop.controller;

import com.example.eshop.dto.cart.AddToCartRequest;
import com.example.eshop.dto.cart.CartResponse;
import com.example.eshop.repository.ProductRepository;
import com.example.eshop.service.CartService;
import com.example.eshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

   @PostMapping("add/{productId}")
    private void addToCart(@PathVariable Long productId, @RequestHeader ("Authorization") String token, @RequestBody AddToCartRequest addToCartRequest) {
       cartService.addToCart(productId, token, addToCartRequest);
   }

}
