package com.example.eshop.controller;

import com.example.eshop.dto.Auth.AuthRequest;
import com.example.eshop.dto.Auth.AuthResponse;
import com.example.eshop.dto.userDto.UserRegisterRequest;
import com.example.eshop.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public void register(@RequestBody UserRegisterRequest userRegisterRequest) {
        authService.register(userRegisterRequest);
    }

    @PostMapping("/login")
    public AuthResponse login (AuthRequest authRequest) {
        return authService.login(authRequest);
    }
}
