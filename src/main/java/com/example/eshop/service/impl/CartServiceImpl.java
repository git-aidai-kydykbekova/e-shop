package com.example.eshop.service.impl;

import com.example.eshop.dto.cart.AddToCartRequest;
import com.example.eshop.dto.cart.CartResponse;
import com.example.eshop.entities.*;
import com.example.eshop.exception.BadRequestException;
import com.example.eshop.mapper.CartMapper;
import com.example.eshop.repository.*;
import com.example.eshop.service.AuthService;
import com.example.eshop.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;

@Service
@AllArgsConstructor

public class CartServiceImpl implements CartService {
    private final AuthService authService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
     private final CartMapper cartMapper;
    private final OrderRepository orderRepository;
    private final ImageRepository imageRepository;


    @Override
    public void addToCart(Long productId, String token, AddToCartRequest addToCartRequest) {
        User user = authService.getUsernameFromToken(token);

        Optional<Cart> cart = cartRepository.findById(user.getCustomer().getCart().getId());
        Optional<Product> product = productRepository.findById(productId);

        CartItem item = new CartItem();

        item.setSku(product.get().getSKU());
        item.setTitle(product.get().getName());
        item.setPrice(product.get().getPrice());
        item.setQuantity(addToCartRequest.getQuantity());
        item.setSubtotal(product.get().getPrice()* addToCartRequest.getQuantity());
        item.setCart(user.getCustomer().getCart());

        CartItem cartItem = cartItemRepository.saveAndFlush(item);
        List<CartItem> items = new ArrayList<>();

        if(cart.get().getCartItems()!= null)items = cart.get().getCartItems();
        items.add(cartItem);
        cart.get().setCartItems(items);
        cartRepository.save(cart.get());
    }


}
