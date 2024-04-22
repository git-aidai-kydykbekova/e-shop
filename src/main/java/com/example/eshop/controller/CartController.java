package com.example.eshop.controller;

import com.example.eshop.dto.AddToCartRequest;
import com.example.eshop.dto.product.ProductRequest;
import com.example.eshop.dto.product.ProductResponse;
import com.example.eshop.repository.ProductRepository;
import com.example.eshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/furniture")
public class CartController {
    private ProductService productService;
    private final ProductRepository productRepository;
   // private final CartService cartService;


//    @PostMapping("/buy/{productId}")
//    public void buyProduct(@PathVariable Long productId, @RequestHeader("Authorization") String token) {
//        productService.buyProduct(productId, token);
//    }
//    @GetMapping("/user/basket")
//    public List<ProductResponse> userBasket(@RequestHeader ("Authorization") String token) {
//        return productService.getMyProducts(token);
//    }
//    @DeleteMapping("/delete/{productId}")
//    public void delete(@PathVariable Long productId,@RequestHeader("Authorization") String token) {
//        productService.deleteProduct(productId, token);
//    }
//    @PutMapping("/update/{productId}")
//    public void updateById(@PathVariable Long productId, @RequestBody ProductRequest productRequest, @RequestHeader ("Authorization") String token){
//        productService.updateById(productId, productRequest, token);
//    }




    @PostMapping("/add/to/cart/")
    public String addToCart(@RequestBody AddToCartRequest request, @RequestHeader("Authorization") String token){
        productService.add(request, token);
        return "product added to cart";
    }


//    @PutMapping("/update")
//    public String updateCart(@RequestBody AddToCartRequest request, @RequestHeader("Authorization") String token){
//        cartService.update(request, token);
//        return "Quantity updated successfully!";
//    }

//    @DeleteMapping("/delete")
//    public String deleteCart(@RequestBody AddToCartRequest request, @RequestHeader("Authorization") String token){
//        cartService.delete(request, token);
//        return "Product deleted successfully!";
//    }
//
//    @GetMapping("/show")
//    public CartResponse showCart(@RequestHeader("Authorization") String token){
//        return cartService.show(token);
//    }
//
//    @PostMapping("/buy")
//    public String buyCart(@RequestHeader("Authorization") String token){
//        cartService.buy(token);
//        return "Products bought succesfully!";
//    } @PutMapping("/update")
//    public String updateCart(@RequestBody AddToCartRequest request, @RequestHeader("Authorization") String token){
//        cartService.update(request, token);
//        return "Quantity updated successfully!";
//    }
//
//    @DeleteMapping("/delete")
//    public String deleteCart(@RequestBody AddToCartRequest request, @RequestHeader("Authorization") String token){
//        cartService.delete(request, token);
//        return "Product deleted successfully!";
//    }
//
//    @GetMapping("/show")
//    public CartResponse showCart(@RequestHeader("Authorization") String token){
//        return cartService.show(token);
//    }
//
//    @PostMapping("/buy")
//    public String buyCart(@RequestHeader("Authorization") String token){
//        cartService.buy(token);
//        return "Products bought succesfully!";
//    }
//
//}
}
