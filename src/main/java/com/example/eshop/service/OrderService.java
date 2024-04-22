package com.example.eshop.service;

import com.example.eshop.dto.order.OrderDetailResponse;
import com.example.eshop.dto.order.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> all(String token);

    OrderDetailResponse getById(String token, Long id);

    void delete(String token, Long id);
}
