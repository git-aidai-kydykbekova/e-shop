package com.example.eshop.service.impl;

import com.example.eshop.dto.checkout.CheckoutRequest;
import com.example.eshop.dto.product.ProductResponse;
import com.example.eshop.entities.User;
import com.example.eshop.service.AuthService;
import com.example.eshop.service.CheckoutService;
import com.example.eshop.service.ProductService;
import com.example.eshop.service.emailSender.EmailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {
    private final AuthService authService;
    private final ProductService productService;
    private final EmailSenderService emailSenderService;


    @Override
    public void checkout(CheckoutRequest checkoutRequest, String token) {
        User user = authService.getUsernameFromToken(token);

        List<ProductResponse> products = productService.getMyProducts(token);
        System.out.println(products);

       // emailSenderService.sendEmail(token,request);
    }
}
