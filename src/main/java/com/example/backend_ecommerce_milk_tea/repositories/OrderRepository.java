package com.example.backend_ecommerce_milk_tea.repositories;

import com.example.backend_ecommerce_milk_tea.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Query("SELECT o FROM Orders o WHERE o.active = true AND o.id = :id")
    Optional<Orders> findByIdAndActiveTrue(@Param("id") Long id);

    @Query("SELECT o FROM Orders o WHERE o.active = true AND o.users.userId = :userId")
    List<Orders> findAllActiveOrdersByUserId(@Param("userId") Long userId);

}
