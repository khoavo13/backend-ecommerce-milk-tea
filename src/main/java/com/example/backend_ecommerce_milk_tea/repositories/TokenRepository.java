package com.example.backend_ecommerce_milk_tea.repositories;

import com.example.backend_ecommerce_milk_tea.models.Token;
import com.example.backend_ecommerce_milk_tea.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token,Long> {
    List<Token> findByUser(Users user);
    Token findByToken(String token);
    Token findByRefreshToken(String refreshToken);
}
