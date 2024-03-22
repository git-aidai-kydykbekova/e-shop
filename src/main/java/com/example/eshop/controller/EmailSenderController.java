package com.example.eshop.controller;

import com.example.eshop.dto.checkout.CheckoutRequest;
import com.example.eshop.dto.checkout.CheckoutResponse;
import com.example.eshop.entities.EmailMessage;
import com.example.eshop.service.CheckoutService;
import com.example.eshop.service.emailSender.EmailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor

public class EmailSenderController {
    private final EmailSenderService emailSenderService;
    private final CheckoutService checkoutService;



    @PostMapping("/email/send")
    public ResponseEntity sendEmail(@RequestBody EmailMessage emailMessage) {
        this.emailSenderService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage());
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/checkout")
    public void checkout(@RequestBody CheckoutRequest checkoutRequest, @RequestHeader("Authorization") String token ) {
        checkoutService.checkout(checkoutRequest, token);
    }
}
