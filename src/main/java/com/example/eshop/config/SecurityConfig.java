package com.example.eshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration     //Обозначает, что этот класс предоставляет конфигурацию для Spring.
@EnableGlobalMethodSecurity(prePostEnabled = true) //Включает глобальную метод-безопасность с использованием аннотаций, таких как @PreAuthorize и @Secured.
@EnableWebSecurity    //Включает конфигурацию безопасности для веб-приложения.
@ComponentScan
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers("/furniture/add/**","/email/**","/checkout/***","/category/add/**", "/category/list","/furniture/user/busket","/furniture/**", "/furniture/update/{productId}", "/furniture/buy/{productId}", "/review/add/{productId}/**","/checkout", "/furniture/myproducts", "/furniture/information/add/**").permitAll()

                )               ;
        return http.build();
    }
}


