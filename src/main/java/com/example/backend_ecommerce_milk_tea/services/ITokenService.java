package com.example.backend_ecommerce_milk_tea.services;

import com.example.backend_ecommerce_milk_tea.models.Token;
import com.example.backend_ecommerce_milk_tea.models.Users;

public interface ITokenService {
    Token addToken(Users user, String token, boolean isMobileDevice);
    Token refreshToken(String refreshToken ,Users user) throws Exception;
}
