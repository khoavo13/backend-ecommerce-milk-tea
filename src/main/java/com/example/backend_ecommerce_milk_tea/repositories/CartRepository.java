package com.example.backend_ecommerce_milk_tea.repositories;

import com.example.backend_ecommerce_milk_tea.models.Carts;
import com.example.backend_ecommerce_milk_tea.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Carts, Long> {
    Carts findByUser(Users user);
}
