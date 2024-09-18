package com.example.backend_ecommerce_milk_tea.services;

import com.example.backend_ecommerce_milk_tea.dtos.UserDTO;
import com.example.backend_ecommerce_milk_tea.models.Users;
import com.example.backend_ecommerce_milk_tea.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    @Override
    public Users getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users addUser(UserDTO userDTO) {
        Users user = Users
                .builder()
                .username(userDTO.getUsername())
                .passwordHash(userDTO.getPasswordHash())
                .email(userDTO.getEmail())
                .phone(userDTO.getPhone())
                .role(userDTO.getRole())
                .build();
        return userRepository.save(user);
    }

    @Override
    public Users updateUser(Long id, UserDTO userDTO) {
        Users user = getUserById(id);
        user.setUsername(userDTO.getUsername());
        user.setPasswordHash(userDTO.getPasswordHash());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setRole(userDTO.getRole());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Users> searchUsers(String gmail) {
        return List.of();
    }
}
