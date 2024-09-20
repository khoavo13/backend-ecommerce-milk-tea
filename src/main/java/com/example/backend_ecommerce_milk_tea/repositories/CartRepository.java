package com.example.backend_ecommerce_milk_tea.repositories;

import com.example.backend_ecommerce_milk_tea.models.Carts;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Carts, Long> {

    // Trả về Optional thay vì List để lấy ra 1 giỏ hàng duy nhất
    Optional<Carts> findByUser_UserId(Long userId);  // Truy vấn dựa trên userId
}
