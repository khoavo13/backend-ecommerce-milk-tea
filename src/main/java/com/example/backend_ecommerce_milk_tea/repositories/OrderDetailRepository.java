package com.example.backend_ecommerce_milk_tea.repositories;
import com.example.backend_ecommerce_milk_tea.models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetails, Long>{
    List<OrderDetails> findByOrderId(Long orderId);

}
