package com.example.eshop.service;

import com.example.eshop.dto.Auth.AuthRequest;
import com.example.eshop.dto.Auth.AuthResponse;
import com.example.eshop.dto.Auth.AuthReviewRequest;
import com.example.eshop.dto.userDto.UserRegisterRequest;
import com.example.eshop.entities.User;

public interface AuthService {
    void register(UserRegisterRequest userRegisterRequest);

    AuthResponse login(AuthRequest authRequest);

    User getUsernameFromToken(String token);

//    void addReview(AuthReviewRequest authReviewRequest, String token);
}
