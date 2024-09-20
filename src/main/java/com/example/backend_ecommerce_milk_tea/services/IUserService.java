package com.example.backend_ecommerce_milk_tea.services;

import com.example.backend_ecommerce_milk_tea.models.Users;

import java.util.List;

public interface IUserService {
    Users createUser(Users user) throws Exception;
    List<Users> getAllUsers();
    Users getUserByUsername(String username) throws Exception;
    String login(String username, String password) throws Exception;
}
