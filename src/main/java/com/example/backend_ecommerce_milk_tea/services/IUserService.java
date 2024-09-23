package com.example.backend_ecommerce_milk_tea.services;

import com.example.backend_ecommerce_milk_tea.models.Users;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Users createUser(Users user) throws Exception;
    List<Users> getAllUsers();
    Users getUserByUsername(String username) throws Exception;
    String login(String username, String password) throws Exception;
    Optional<Users> findUserByUsernameAndPassword(String username, String email);
}
