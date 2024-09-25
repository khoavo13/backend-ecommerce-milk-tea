package com.example.backend_ecommerce_milk_tea.services;

import com.example.backend_ecommerce_milk_tea.dtos.OrderDTO;
import com.example.backend_ecommerce_milk_tea.dtos.OrderDetailDTO;
import com.example.backend_ecommerce_milk_tea.exceptions.DataNotFoundException;
import com.example.backend_ecommerce_milk_tea.models.OrderStatus;
import com.example.backend_ecommerce_milk_tea.models.Orders;
import org.springframework.dao.DataAccessException;


import java.util.List;
public interface IOrderService {
    // Đặt một đơn hàng mới
    Orders createOrder(OrderDTO orderDTO) throws DataNotFoundException;

    // Lấy một đơn hàng theo ID
    Orders getOrderById(Long orderId);

    // Lấy tất cả các đơn hàng của người dùng
    List<Orders> getOrdersByUserId(Long userId);

    // Xoá mềm đơn hàng
    void softDeleteOrder(Long orderId);

    // Lấy tất cả đơn hàng active (không bị xoá) của người dùng
    List<Orders> getActiveOrdersByUserId(Long userId);

    // Cập nhật trạng thái đơn hàng
    Orders updateOrderStatus(Long orderId, String status);

}
