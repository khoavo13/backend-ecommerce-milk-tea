package com.example.backend_ecommerce_milk_tea.models;

public class PaymentType {
    public static final String VNPAY = "VNPay";
    public static final String CASH = "Tiền mặt";
    public static final String BANK_TRANSFER = "Chuyển khoản ngân hàng";

    public static boolean isValidType(String type) {
        return VNPAY.equals(type) || CASH.equals(type) || BANK_TRANSFER.equals(type);
    }
}
