package com.example.eshop.service.impl;
import com.example.eshop.dto.checkout.CheckoutRequest;
import com.example.eshop.entities.Checkout;
import com.example.eshop.entities.User;
import com.example.eshop.repository.CheckoutRepository;
import com.example.eshop.service.AuthService;
import com.example.eshop.service.CheckoutService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {
    private final AuthService authService;
    private final CheckoutRepository checkoutRepository;



    @Override
    public void checkout(CheckoutRequest checkoutRequest, String token) {
        User user = authService.getUsernameFromToken(token);
        Checkout checkout = new Checkout();
        checkout.setCity(checkoutRequest.getCity());
        checkout.setEmail(checkoutRequest.getEmail());
        checkout.setCountry(checkoutRequest.getCountry());
        checkout.setNumber(checkoutRequest.getNumber());
        checkout.setProvince(checkoutRequest.getProvince());
        checkout.setSurname(checkoutRequest.getSurname());
        checkout.setZipCode(checkoutRequest.getZipCode());
        checkout.setFirstName(checkoutRequest.getFirstName());
        checkout.setCompanyName(checkoutRequest.getCompanyName());

        checkoutRepository.save(checkout);


//        List<ProductResponse> products = productService.getMyProducts(token);
//        System.out.println(products);
//        String cart =  products.toString();
//        emailSenderService.sendEmail(email,"VERIFY",cart);

       // emailSenderService.sendEmail(token,request);
    }
}
