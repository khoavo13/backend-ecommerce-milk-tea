package com.example.backend_ecommerce_milk_tea.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payments extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Long orderId;

    private String paymentType; // ["VNPay", "Tiền mặt", "Chuyển khoản ngân hàng"

    private String paymentStatus; // ["Đã thanh toán", "Chờ xử lý", "Thất bại"]

    private Double totalPrice; // số tiền thanh toán
}
