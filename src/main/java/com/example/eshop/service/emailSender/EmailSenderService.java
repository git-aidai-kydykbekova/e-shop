package com.example.eshop.service.emailSender;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String massage);
}
