package com.example.eshop.service.impl;


import com.example.eshop.config.JwtService;
import com.example.eshop.dto.Auth.AuthRequest;
import com.example.eshop.dto.Auth.AuthResponse;
import com.example.eshop.dto.user.UserRegisterRequest;
import com.example.eshop.entities.Customer;
import com.example.eshop.entities.User;
import com.example.eshop.exception.BadCredentialsException;
import com.example.eshop.repository.UserRepository;
import com.example.eshop.role.Role;
import com.example.eshop.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public void register(UserRegisterRequest userRegisterRequest) {
        if(userRepository.findByEmail(userRegisterRequest.getEmail()).isPresent()){
            throw new BadCredentialsException("user with email: "+userRegisterRequest.getEmail()+" is already exist!");
        }
        User user = new User();
        user.setRole(Role.Customer);
        user.setEmail(userRegisterRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));

        Customer customer = new Customer();
        customer.setAge(userRegisterRequest.getAge());
        customer.setName(userRegisterRequest.getName());
        customer.setUser(user);
        user.setCustomer(customer);

        userRepository.save(user);

    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        Optional<User> user = userRepository.findByEmail(authRequest.getEmail());
        if(user.isEmpty()) {
            throw new BadCredentialsException("user is not found");
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));

        }catch (org.springframework.security.authentication.BadCredentialsException e){
            throw new BadCredentialsException("user not found");
        }
        return convertToResponse(user);

    }
    private AuthResponse convertToResponse(Optional<User> user) {
        AuthResponse authLoginResponse = new AuthResponse();
        authLoginResponse.setEmail(user.get().getEmail());
        authLoginResponse.setId(user.get().getId());
        if (user.get().getRole().equals(Role.Customer))
            authLoginResponse.setName(user.get().getCustomer().getName());
        Map<String, Object> extraClaims = new HashMap<>();

        String token = jwtService.generateToken(extraClaims, user.get());
        authLoginResponse.setToken(token);

        return authLoginResponse;
    }
}
