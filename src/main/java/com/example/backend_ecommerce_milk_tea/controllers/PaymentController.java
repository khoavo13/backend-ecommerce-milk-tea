package com.example.backend_ecommerce_milk_tea.controllers;

import com.example.backend_ecommerce_milk_tea.dtos.PaymentDTO;
import com.example.backend_ecommerce_milk_tea.exceptions.DataNotFoundException;
import com.example.backend_ecommerce_milk_tea.models.Payments;
import com.example.backend_ecommerce_milk_tea.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

//    @PostMapping("/create")
//    public ResponseEntity<?> createPayment(@Valid @RequestBody PaymentDTO paymentDTO, BindingResult result) {
//        try {
//            if (result.hasErrors()) {
//                return ResponseEntity.badRequest().body("Invalid input");
//            }
//            Payments newPayment = paymentService.createPayment(paymentDTO);
//            return new ResponseEntity<>(newPayment, HttpStatus.CREATED);
//        } catch (DataNotFoundException e) {
//            // Trả về lỗi nếu không tìm thấy Order
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    // API tạo Payment mới
    @PostMapping("/create")
    public ResponseEntity<?> createPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        try {
            Payments payment = paymentService.createPayment(paymentDTO);
            return new ResponseEntity<>(payment, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // API lấy Payment theo Order ID
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getPaymentByOrderId(@PathVariable Long orderId) {
        try {
            Payments payment = paymentService.getPaymentByOrderId(orderId);
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // API cập nhật trạng thái Payment
    @PutMapping("/update/{paymentId}/status")
    public ResponseEntity<?> updatePaymentStatus(@PathVariable Long paymentId, @RequestBody String status) {
        try {
            // Loại bỏ các khoảng trắng hoặc ký tự thừa ngay khi nhận được request
            status = status.trim();

            Payments payment = paymentService.updatePaymentStatus(paymentId, status);
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

