package com.example.eshop.dto.Auth;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthResponse {
    private Long id;
    private String email;
    private String name;
    private String token;
}
