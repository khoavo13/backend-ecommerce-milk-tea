package com.example.backend_ecommerce_milk_tea.models;

public class PaymentStatus {
    public static final String PAID = "Đã thanh toán";
    public static final String PENDING = "Chờ xử lý";
    public static final String FAILED = "Thất bại";

    public static boolean isValidStatus(String status) {
        return PAID.equals(status) || PENDING.equals(status) || FAILED.equals(status);
    }
}
