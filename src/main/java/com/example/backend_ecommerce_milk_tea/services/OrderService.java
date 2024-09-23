package com.example.backend_ecommerce_milk_tea.services;

import com.example.backend_ecommerce_milk_tea.dtos.OrderDTO;
import com.example.backend_ecommerce_milk_tea.dtos.OrderDetailDTO;
import com.example.backend_ecommerce_milk_tea.exceptions.DataNotFoundException;
import com.example.backend_ecommerce_milk_tea.models.Orders;
import com.example.backend_ecommerce_milk_tea.models.OrderStatus;
import com.example.backend_ecommerce_milk_tea.models.Products;
import com.example.backend_ecommerce_milk_tea.models.Users;
import com.example.backend_ecommerce_milk_tea.repositories.OrderRepository;
import com.example.backend_ecommerce_milk_tea.repositories.ProductRepository;
import com.example.backend_ecommerce_milk_tea.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.time.ZoneId;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    @Override
    public Orders createOrder(OrderDTO orderDTO) {
        // Tìm người dùng dựa trên userId từ orderDTO
        Users user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy người dùng với ID: " + orderDTO.getUserId()));

        // Tạo đơn hàng mới từ các thông tin trong OrderDTO
        Orders order = new Orders();
        order.setUsers(user);
        order.setFullName(orderDTO.getFullname());
        order.setEmail(orderDTO.getEmail());
        order.setPhoneNumber(orderDTO.getPhoneNumber());
        order.setAddress(orderDTO.getAddress());
        order.setNote(orderDTO.getNote());
        order.setShippingMethod(orderDTO.getShippingMethod());
        order.setShippingAddress(orderDTO.getShippingAddress());
        order.setShippingDate(orderDTO.getShippingDate());
        order.setTrackingNumber(orderDTO.getTrackingNumber());
        order.setPaymentMethod(orderDTO.getPaymentMethod());
        order.setStatus("NEW"); // Đơn hàng mới có trạng thái mặc định là "NEW"
        order.setActive(true); // Đơn hàng mới luôn active (chưa bị xoá)

        // Tính tổng tiền từ danh sách OrderDetailDTO
        Long totalMoney = 0L;
        for (OrderDetailDTO detailDTO : orderDTO.getOrderDetailsDTO()) {
            Products product = productRepository.findById(detailDTO.getProductId())
                    .orElseThrow(() -> new DataNotFoundException("Không tìm thấy sản phẩm với ID: " + detailDTO.getProductId()));

            //totalMoney += detailDTO.getPrice() * detailDTO.getNumberOfProducts();
            totalMoney += detailDTO.getTotalMoney();
        }
        order.setTotalMoney(totalMoney);

        // Lưu đơn hàng vào cơ sở dữ liệu
        Orders savedOrder = orderRepository.save(order);
        return savedOrder;
    }

    @Override
    public Orders getOrderById(Long orderId) {
       // Lấy đơn hàng theo ID và chỉ lấy các đơn hàng không bị xoá mềm (active = true)
        return orderRepository.findByIdAndActiveTrue(orderId)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy đơn hàng với ID: " + orderId + " hoặc đơn hàng đã bị xoá."));
    }

    @Override
    public List<Orders> getOrdersByUserId(Long userId) {
        // Lấy danh sách các đơn hàng của người dùng với active = true (không bị ẩn)
        return orderRepository.findAllActiveOrdersByUserId(userId);
    }

    @Override
    public void softDeleteOrder(Long orderId) {
        // Xoá mềm đơn hàng bằng cách đặt active = false
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy đơn hàng với ID: " + orderId));
        order.setActive(false); // Đặt active = false để xoá mềm đơn hàng
        orderRepository.save(order); // Lưu đơn hàng đã được xoá mềm
    }

    @Override
    public List<Orders> getActiveOrdersByUserId(Long userId) {
        // Lấy tất cả đơn hàng active (không bị xoá mềm) của người dùng
        return orderRepository.findAllActiveOrdersByUserId(userId);
    }

    @Override
    public Orders updateOrderStatus(Long orderId, OrderStatus status) {
        // Tìm đơn hàng theo ID và chỉ lấy các đơn hàng không bị xoá mềm
        Orders order = orderRepository.findByIdAndActiveTrue(orderId)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy đơn hàng với ID: " + orderId + " hoặc đơn hàng đã bị xoá mềm."));

        // Kiểm tra và cập nhật trạng thái đơn hàng
        if (status.equals(OrderStatus.PENDING)){
            order.setStatus(OrderStatus.PENDING);
        } else if (status.equals(OrderStatus.PROCESSING)){
            order.setStatus(OrderStatus.PROCESSING);
        } else if (status.equals(OrderStatus.SHIPPED)){
            order.setStatus(OrderStatus.SHIPPED);
        } else if(status.equals(OrderStatus.DELIVERED)){
            order.setStatus(OrderStatus.DELIVERED);
        } else if(status.equals(OrderStatus.CANCELLED)) {
            order.setStatus(OrderStatus.CANCELLED);
        } else {
            throw new IllegalArgumentException("Trạng thái đơn hàng không hợp lệ.");
        }

        return orderRepository.save(order); // Lưu đơn hàng sau khi cập nhật
    }
}
