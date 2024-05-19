package com.example.eshop.service.impl;

import com.example.eshop.dto.order.OrderDetailResponse;
import com.example.eshop.dto.order.OrderResponse;
import com.example.eshop.entities.Image;
import com.example.eshop.entities.Order;
import com.example.eshop.entities.User;
import com.example.eshop.exception.NotFoundException;
import com.example.eshop.mapper.OrderMapper;
import com.example.eshop.repository.OrderRepository;
import com.example.eshop.repository.UserRepository;
import com.example.eshop.service.AuthService;
import com.example.eshop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final AuthService authService;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
//    @Override
//    public List<OrderResponse> all(String token) {
//        User user = authService.getUsernameFromToken(token);
//        return orderMapper.toDtos(user.getOrders());
//    }
//
//    @Override
//    public OrderDetailResponse getById(String token, Long id) {
//        User user = authService.getUsernameFromToken(token);
//        Optional<Order> order = orderRepository.findById(id);
//        if(order.isEmpty() || order.get().getUser() != user)
//            throw new NotFoundException("Order not found!", HttpStatus.NOT_FOUND);
//        return orderMapper.toDetailDto(order.get());
//    }
//
//    @Override
//    public void delete(String token, Long id) {
//        User user = authService.getUsernameFromToken(token);
//        Optional<Order> order = orderRepository.findById(id);
//        if(order.isEmpty() || order.get().getUser() != user)
//            throw new NotFoundException("Order not found!", HttpStatus.NOT_FOUND);
//        List<Order> orders = user.getOrders();
//        orders.remove(order.get());
//        order.get().setUser(null);
//
//        if(order.get().getImage() != null) {
//            Image image = order.get().getImage();
//            orders = image.getOrders();
//            orders.remove(order.get());
//            order.get().setImage(null);
//        }
//
//        orderRepository.delete(order.get());
//
//        userRepository.save(user);
//    }
}
