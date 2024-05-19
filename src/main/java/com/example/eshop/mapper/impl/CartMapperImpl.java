package com.example.eshop.mapper.impl;

import com.example.eshop.dto.cart.CartResponse;
import com.example.eshop.entities.Cart;
import com.example.eshop.entities.CartItem;
import com.example.eshop.mapper.CartMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartMapperImpl implements CartMapper {
    public CartResponse toDto(Cart cart) {
        CartResponse response = new CartResponse();
        List<String> titles = new ArrayList<>();
        List<Integer> prices = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();
        List<Integer> subtotals = new ArrayList<>();
        List<String> imageNames = new ArrayList<>();
        Integer total = 0;
        List<CartItem> items = cart.getCartItems();
        for(CartItem item: items) {
            titles.add(item.getTitle());
            prices.add(item.getPrice());
            quantities.add(item.getQuantity());
            subtotals.add(item.getSubtotal());
            total += item.getSubtotal();
            if(item.getImage() != null)
                imageNames.add(item.getImage().getName());
        }
        response.setTitles(titles);
        response.setPrices(prices);
        response.setQuantities(quantities);
        response.setSubtotals(subtotals);
        response.setTotal(total);
        response.setImageNames(imageNames);

        return response;
    }
}
