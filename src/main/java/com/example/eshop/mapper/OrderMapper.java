package com.example.eshop.mapper;

import com.example.eshop.dto.order.OrderDetailResponse;
import com.example.eshop.dto.order.OrderResponse;
import com.example.eshop.entities.Order;

import java.util.List;

public interface OrderMapper {
    List<OrderResponse> toDtos(List<Order> orders);

    OrderDetailResponse toDetailDto(Order order);
}
