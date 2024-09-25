package com.example.backend_ecommerce_milk_tea.repositories;

import com.example.backend_ecommerce_milk_tea.models.CartItems;
import com.example.backend_ecommerce_milk_tea.models.Carts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItems, Long> {
    void deleteAllByCart(Carts cart);
}
