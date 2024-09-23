package com.example.backend_ecommerce_milk_tea.services;


import com.example.backend_ecommerce_milk_tea.dtos.OrderDetailDTO;
import com.example.backend_ecommerce_milk_tea.exceptions.DataNotFoundException;
import com.example.backend_ecommerce_milk_tea.models.OrderDetails;
import org.springframework.dao.DataAccessException;

import java.util.List;
public interface IOrderDetailService {
    // Thêm chi tiết đơn hàng
    OrderDetails addOrderDetail(OrderDetailDTO orderDetailDTO);

    // Lấy chi tiết đơn hàng theo Order ID
    List<OrderDetails> getOrderDetailsByOrderId(Long orderId);

    // Cập nhật chi tiết đơn hàng
    OrderDetails updateOrderDetail(Long orderDetailId, OrderDetailDTO orderDetailDTO);

    // Xoá chi tiết đơn hàng
    void removeOrderDetail(Long orderDetailId);
}
