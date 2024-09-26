package com.example.backend_ecommerce_milk_tea.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDTO {
    private Long paymentId;

    @Min(value = 1, message = "Order ID must be > 0")
    private Long orderId;

    @NotBlank(message = "Payment type is required")
    private String paymentType; // ["VNPay", "Tiền mặt", "Chuyển khoản ngân hàng"]

    @NotBlank(message = "Payment status is required")
    private String paymentStatus; // ["Đã thanh toán", "Chờ xử lý", "Thất bại"]

    @Min(value = 0, message = "Total price must be >= 0")
    private Long totalPrice;
}

