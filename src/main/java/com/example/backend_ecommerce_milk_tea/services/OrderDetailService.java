package com.example.backend_ecommerce_milk_tea.services;

import com.example.backend_ecommerce_milk_tea.dtos.OrderDetailDTO;
import com.example.backend_ecommerce_milk_tea.exceptions.DataNotFoundException;
import com.example.backend_ecommerce_milk_tea.models.OrderDetails;
import com.example.backend_ecommerce_milk_tea.models.Orders;
import com.example.backend_ecommerce_milk_tea.models.Products;
import com.example.backend_ecommerce_milk_tea.repositories.OrderDetailRepository;
import com.example.backend_ecommerce_milk_tea.repositories.OrderRepository;
import com.example.backend_ecommerce_milk_tea.repositories.ProductRepository;

import java.util.List;

public class OrderDetailService implements IOrderDetailService{

    private OrderDetailRepository orderDetailRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    @Override
    public OrderDetails addOrderDetail(OrderDetailDTO orderDetailDTO) {
        // Tìm đơn hàng theo orderId từ OrderDetailDTO
        Orders order = orderRepository.findById(orderDetailDTO.getOrderId())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy đơn hàng với ID: " + orderDetailDTO.getOrderId()));

        // Tìm sản phẩm theo productId từ OrderDetailDTO
        Products product = productRepository.findById(orderDetailDTO.getProductId())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy sản phẩm với ID: " + orderDetailDTO.getProductId()));

        // Tạo chi tiết đơn hàng mới từ các thông tin trong OrderDetailDTO
        OrderDetails orderDetail = new OrderDetails();
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setPrice(orderDetailDTO.getPrice());
        orderDetail.setNumberOfProducts(orderDetailDTO.getNumberOfProducts());
        orderDetail.setTotalMoney(orderDetailDTO.getPrice() * orderDetailDTO.getNumberOfProducts());
        orderDetail.setSize(orderDetailDTO.getSize());

        // Lưu chi tiết đơn hàng vào cơ sở dữ liệu
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
        existingOrderDetail.setTotalMoney(orderDetailDTO.getPrice() * orderDetailDTO.getNumberOfProducts());
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
