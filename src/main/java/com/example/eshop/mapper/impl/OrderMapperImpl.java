package com.example.eshop.mapper.impl;

import com.example.eshop.dto.order.OrderDetailResponse;
import com.example.eshop.dto.order.OrderResponse;
import com.example.eshop.entities.Order;
import com.example.eshop.mapper.ImageMapper;
import com.example.eshop.mapper.OrderMapper;
import com.example.eshop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class OrderMapperImpl implements OrderMapper {
    private final ImageMapper imageMapper;
    @Override
    public List<OrderResponse> toDtos(List<Order> orders) {
        List<OrderResponse> responses = new ArrayList<>();
        for(Order order: orders)
            responses.add(toDto(order));
        return responses;
    }

    @Override
    public OrderDetailResponse toDetailDto(Order order) {
        OrderDetailResponse response = new OrderDetailResponse();
        response.setId(order.getId());
        response.setSku(order.getSku());
        response.setTitle(order.getTitle());
        response.setPrice(order.getPrice());
        response.setQuantity(order.getQuantity());
        response.setCreateDate(order.getCreateDate());
        response.setTotal(order.getTotal());
        if(order.getImage() != null)
            response.setImage(imageMapper.toDetailDto(order.getImage()));
        return response;
    }

    private OrderResponse toDto(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setTitle(order.getTitle());
        response.setPrice(order.getPrice());
        response.setCreateDate(order.getCreateDate());
        if(order.getImage() != null)
            response.setImageName(order.getImage().getName());
        return response;
    }
}
