package com.example.eshop.service.impl;
import com.example.eshop.dto.checkout.CheckoutRequest;
import com.example.eshop.entities.Checkout;
import com.example.eshop.entities.User;
import com.example.eshop.repository.CheckoutRepository;
import com.example.eshop.repository.UserRepository;
import com.example.eshop.service.AuthService;
import com.example.eshop.service.CheckoutService;
import com.example.eshop.service.emailSender.EmailSenderService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {
    private final AuthService authService;
    private final CheckoutRepository checkoutRepository;
    private final EmailSenderService emailSenderService;
    private final UserRepository userRepository;




    @Override
    public void checkout(CheckoutRequest checkoutRequest, String token) {

        String code = emailSenderService.generateCode();

        try {
            emailSenderService.sendCodeToEmail(checkoutRequest.getEmail(), code);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send code, please try again");
        }
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
        checkout.setCode(code);
        checkout.setCodeGeneratedTime(LocalDateTime.now());


        //userRepository.save(user);
        checkoutRepository.save(checkout);

    }

    @Override
    public String verifyCode(String email, String code) {
        Checkout checkout = new Checkout();
        Optional <User> user = userRepository.findByEmail(email);
        if(user.isEmpty()) {
            throw new RuntimeException("User is not founded " + email);
        }
        if(checkout.getCode().equals(code) && Duration.between(checkout.getCodeGeneratedTime(),
                LocalDateTime.now()).getSeconds() < (10 * 60)) {
            checkout.setVerify(true);
            checkoutRepository.save(checkout);
            return "code is verified";
        }

        return "Please regenerate code and try  again";
    }
}
