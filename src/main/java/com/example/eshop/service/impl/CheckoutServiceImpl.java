package com.example.eshop.service.impl;
import com.example.eshop.dto.checkout.CheckoutRequest;
import com.example.eshop.entities.Checkout;
import com.example.eshop.entities.User;
import com.example.eshop.exception.BadRequestException;
import com.example.eshop.repository.CheckoutRepository;
import com.example.eshop.repository.UserRepository;
import com.example.eshop.service.AuthService;
import com.example.eshop.service.CheckoutService;
import com.example.eshop.service.emailSender.EmailSenderService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutRepository checkoutRepository;
    private final EmailSenderService emailSenderService;
    private final AuthService authService;
    private final UserRepository userRepository;




    @Override
    public void checkout(CheckoutRequest checkoutRequest, String token) {
        User customer = authService.getUsernameFromToken(token);
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
        checkout.setCustomer(checkout.getCustomer());///
        checkout.setCode(code);
        System.out.println(checkout.getCode());
        checkout.setCodeGeneratedTime(LocalDateTime.now());

        checkoutRepository.save(checkout);
        userRepository.save(customer);

    }

    @Override
    public void verifyCode(Long checkId, String token, Long code) {

        User user = authService.getUsernameFromToken(token);
        Optional<Checkout> checkout = checkoutRepository.findById(checkId);
        System.out.println(checkout.get().getCode());
        System.out.println(code);// code = null why???
        if(Objects.equals(checkout.get().getCode(),code)){
            checkout.get().setVerify(true);
            userRepository.save(user);
            checkoutRepository.save(checkout.get());
        }
        else {
            throw new BadRequestException("Code is wrong!");
        }
    }
}
