package com.example.backend_ecommerce_milk_tea.services;

import com.example.backend_ecommerce_milk_tea.dtos.UserDTO;
import com.example.backend_ecommerce_milk_tea.models.Users;

import java.util.List;

public interface IUserService {
    Users getUserById(Long id);
    List<Users> getAllUsers();
    Users addUser(UserDTO userDTO);
    Users updateUser(Long id,UserDTO userDTO);
    void deleteUser(Long id);
    List<Users> searchUsers(String gmail);
}
