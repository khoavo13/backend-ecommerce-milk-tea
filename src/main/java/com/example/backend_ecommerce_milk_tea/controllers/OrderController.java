package com.example.backend_ecommerce_milk_tea.controllers;

import com.example.backend_ecommerce_milk_tea.dtos.OrderDTO;
import com.example.backend_ecommerce_milk_tea.dtos.UpdateStatusDTO;
import com.example.backend_ecommerce_milk_tea.models.Orders;
import com.example.backend_ecommerce_milk_tea.responses.ApiResponse;
import com.example.backend_ecommerce_milk_tea.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    private final OrderService orderService;

    // Tạo đơn hàng mới
    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDTO orderDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
            ApiResponse apiResponse = ApiResponse.builder()
                    .data(errors)
                    .message("Validation failed")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .build();
            return ResponseEntity.badRequest().body(apiResponse);
        }

        Orders order = orderService.createOrder(orderDTO);
        ApiResponse apiResponse = ApiResponse.builder()
                .data(order)
                .message("Inserted successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(apiResponse);

    }

    // Lấy đơn hàng theo ID
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderById(@Valid @PathVariable("orderId") Long orderId) {
        try {
            Orders order = orderService.getOrderById(orderId);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Lấy tất cả các đơn hàng của người dùng
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getOrdersByUserId(@Valid @PathVariable("userId") Long userId) {
        try {
            List<Orders> orders = orderService.getOrdersByUserId(userId);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    // Xóa mềm đơn hàng (ẩn đi)
    @DeleteMapping("delete/{orderId}")
    public ResponseEntity<Void> softDeleteOrder(@PathVariable Long orderId) {
        orderService.softDeleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    // Cập nhật trạng thái đơn hàng
    @PutMapping("update/{orderId}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long orderId, @Valid @RequestBody UpdateStatusDTO statusDTO) {
        try {
            Orders updatedOrder = orderService.updateOrderStatus(orderId, statusDTO.getStatus());
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
