package com.example.backend_ecommerce_milk_tea.repositories;

import com.example.backend_ecommerce_milk_tea.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    boolean existsByUsername(String username);
    Optional<Users> findByUsernameAndEmail(String username, String email);
    Optional<Users> findById(Long userId);

}
