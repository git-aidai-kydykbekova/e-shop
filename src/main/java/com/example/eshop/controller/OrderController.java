package com.example.eshop.controller;

import com.example.eshop.dto.order.OrderDetailResponse;
import com.example.eshop.dto.order.OrderResponse;
import com.example.eshop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/all")
    public List<OrderResponse> all(@RequestHeader("Authorization") String token){
        return orderService.all(token);
    }

    @GetMapping("/{id}")
    public OrderDetailResponse detail(@RequestHeader("Authorization") String token, @PathVariable Long id){
        return orderService.getById(token, id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@RequestHeader("Authorization") String token, @PathVariable Long id){
        orderService.delete(token, id);
        return "Order deleted successfully!";
    }

}
