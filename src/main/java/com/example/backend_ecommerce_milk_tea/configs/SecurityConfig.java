package com.example.backend_ecommerce_milk_tea.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .requestMatchers(HttpMethod.POST,"/api/user/login").permitAll()
                .requestMatchers(HttpMethod.GET,"/api/user/list/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/user/search/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/api/category/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/user/register").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/category").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/category").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/api/category/addProduct/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/api/cart/**").hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/api/orders/createOrder").hasRole("ADMIN")
                .requestMatchers("/api/orders/**", "/api/orderDetails/**").hasRole("ADMIN")
                //.requestMatchers("/api/orders/**").permitAll()


                .anyRequest().authenticated();
                return http.build();
    }
}

