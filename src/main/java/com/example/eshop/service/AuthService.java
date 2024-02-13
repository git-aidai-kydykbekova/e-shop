package com.example.eshop.service;

import com.example.eshop.dto.Auth.AuthRequest;
import com.example.eshop.dto.Auth.AuthResponse;
import com.example.eshop.dto.user.UserRegisterRequest;

public interface AuthService {
    void register(UserRegisterRequest userRegisterRequest);

    AuthResponse login(AuthRequest authRequest);
}
