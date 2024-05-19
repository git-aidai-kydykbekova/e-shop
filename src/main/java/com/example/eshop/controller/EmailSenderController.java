package com.example.eshop.controller;
import com.example.eshop.dto.checkout.CheckoutRequest;
import com.example.eshop.entities.EmailMessage;
import com.example.eshop.service.CheckoutService;
import com.example.eshop.service.emailSender.EmailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

public class EmailSenderController {
    private final EmailSenderService emailSenderService;
    private final CheckoutService checkoutService;



    @PostMapping("/email/send")
    public ResponseEntity sendEmail(@RequestBody EmailMessage emailMessage) {
        emailSenderService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage());
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/checkout")
    public void checkout(@RequestBody CheckoutRequest checkoutRequest, @RequestHeader("Authorization") String token) {
        checkoutService.checkout(checkoutRequest, token);
    }
    @PostMapping("/verify-order/{checkId}")
    public String verifyOrder( @RequestParam Long code,@RequestHeader ("Authorization") String token, @PathVariable Long checkId) {
        checkoutService.verifyCode(checkId,token, code);
        return "Your email is linked";
    }

}
