package com.example.backend_ecommerce_milk_tea.services;

import com.example.backend_ecommerce_milk_tea.dtos.OrderDetailDTO;
import com.example.backend_ecommerce_milk_tea.exceptions.DataNotFoundException;
import com.example.backend_ecommerce_milk_tea.models.OrderDetails;
import com.example.backend_ecommerce_milk_tea.models.Orders;
import com.example.backend_ecommerce_milk_tea.models.Products;
import com.example.backend_ecommerce_milk_tea.repositories.OrderDetailRepository;
import com.example.backend_ecommerce_milk_tea.repositories.OrderRepository;
import com.example.backend_ecommerce_milk_tea.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService implements IOrderDetailService{

    @Autowired
    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final ProductRepository productRepository;

    @Override
    public OrderDetails addOrderDetail(OrderDetailDTO orderDetailDTO) {
        Orders order = orderRepository.findById(orderDetailDTO.getOrderId())
                .orElseThrow(() -> new DataNotFoundException("Order not found with ID: " + orderDetailDTO.getOrderId()));

        Products product = productRepository.findById(orderDetailDTO.getProductId())
                .orElseThrow(() -> new DataNotFoundException("Product not found with ID: " + orderDetailDTO.getProductId()));

        OrderDetails orderDetail = new OrderDetails();
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setPrice(orderDetailDTO.getPrice());
        orderDetail.setNumberOfProducts(orderDetailDTO.getNumberOfProducts());

        orderDetail.setSize(orderDetailDTO.getSize());

        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public List<OrderDetails> getOrderDetailsByOrderId(Long orderId) {
        // Lấy danh sách chi tiết đơn hàng theo orderId
        return orderDetailRepository.findByOrderId(orderId);
    }

    @Override
    public OrderDetails updateOrderDetail(Long orderDetailId, OrderDetailDTO orderDetailDTO) {
        // Tìm chi tiết đơn hàng theo ID
        OrderDetails existingOrderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy chi tiết đơn hàng với ID: " + orderDetailId));

        // Cập nhật thông tin chi tiết đơn hàng từ OrderDetailDTO
        existingOrderDetail.setPrice(orderDetailDTO.getPrice());
        existingOrderDetail.setNumberOfProducts(orderDetailDTO.getNumberOfProducts());
        existingOrderDetail.setSize(orderDetailDTO.getSize());

        // Lưu chi tiết đơn hàng đã cập nhật
        return orderDetailRepository.save(existingOrderDetail);
    }

    @Override
    public void removeOrderDetail(Long orderDetailId) {
        // Tìm chi tiết đơn hàng theo ID
        OrderDetails orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy chi tiết đơn hàng với ID: " + orderDetailId));

        // Xoá chi tiết đơn hàng
        orderDetailRepository.delete(orderDetail);
    }
}
