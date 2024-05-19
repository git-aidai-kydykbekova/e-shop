package com.example.eshop.service.emailSender;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@AllArgsConstructor
@Service
public class EmailSenderService {

    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("aidai.kydykbekova@alatoo.edu.kg");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        mailSender.send(simpleMailMessage);
    }
    public String generateCode(){
        Random random = new Random();
        int randomNumber = random.nextInt(999999);
        String output = Integer.toString(randomNumber);
        while(output.length() < 6) {
            output = "0" + output;
        }
        return output;
    }
    public void sendCodeToEmail(String email, String code) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("VERIFY CODE");
        mimeMessageHelper.setText("""
            <div>
                <a href = "http://localhost:8080/verify-order?email=%s&code=%s" target="_blank">click link to verify </a>
            </div>
                """.formatted(email, code), true);
        mailSender.send(mimeMessage);
    }


}
