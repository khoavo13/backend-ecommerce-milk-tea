package com.example.backend_ecommerce_milk_tea.services;

import com.example.backend_ecommerce_milk_tea.dtos.PaymentDTO;
import com.example.backend_ecommerce_milk_tea.exceptions.DataNotFoundException;
import com.example.backend_ecommerce_milk_tea.models.Orders;
import com.example.backend_ecommerce_milk_tea.models.Payments;
import com.example.backend_ecommerce_milk_tea.repositories.OrderRepository;
import com.example.backend_ecommerce_milk_tea.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Override
    public Payments createPayment(PaymentDTO paymentDTO) {
        // Kiểm tra xem Order có tồn tại hay không
        Orders order = orderRepository.findById(paymentDTO.getOrderId())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy Order với ID: " + paymentDTO.getOrderId()));

        // Kiểm tra xem Payment đã tồn tại với Order hay chưa
        Optional<Payments> existingPayment = paymentRepository.findByOrder(order);
        if (existingPayment.isPresent()) {
            throw new IllegalArgumentException("Order đã có Payment, không thể tạo mới.");
        }

        // Tạo mới Payment
        Payments payment = Payments.builder()
                .order(order)
                .paymentType(paymentDTO.getPaymentType())
                .paymentStatus(paymentDTO.getPaymentStatus())
                .totalPrice(paymentDTO.getTotalPrice())
                .build();

        return paymentRepository.save(payment);
    }

    @Override
    public Payments getPaymentByOrderId(Long orderId) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy Order với ID: " + orderId));

        return paymentRepository.findByOrder(order)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy Payment cho Order với ID: " + orderId));
    }

    @Override
    public Payments updatePaymentStatus(Long paymentId, String status) {
        Payments payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy Payment với ID: " + paymentId));

        // Loại bỏ các ký tự thừa như khoảng trắng và xuống dòng
        status = status.trim();

        payment.setPaymentStatus(status);
        return paymentRepository.save(payment);
    }

}
