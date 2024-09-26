package com.example.backend_ecommerce_milk_tea.services;

import com.example.backend_ecommerce_milk_tea.dtos.PaymentDTO;
import com.example.backend_ecommerce_milk_tea.models.Payments;

public interface IPaymentService {
    Payments createPayment(PaymentDTO paymentDTO);

    Payments getPaymentByOrderId(Long orderId);

    Payments updatePaymentStatus(Long paymentId, String status);
}
