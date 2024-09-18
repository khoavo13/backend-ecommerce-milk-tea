package com.example.backend_ecommerce_milk_tea.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // khởi tạo giá trị id tự động bắt đầu từ 1
    private Long orderId;

    private Long userId;

    private LocalDateTime orderDate;

    private Double totalPrice; // tổng giá trị đơn hàng

    private String status; // ["Chưa đặt", "Đang xử lý", "Hoàn thành", "Hủy bỏ"]
}
