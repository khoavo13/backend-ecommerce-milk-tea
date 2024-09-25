package com.example.backend_ecommerce_milk_tea.controllers;


import com.example.backend_ecommerce_milk_tea.dtos.OrderDetailDTO;
import com.example.backend_ecommerce_milk_tea.exceptions.DataNotFoundException;
import com.example.backend_ecommerce_milk_tea.models.OrderDetails;
import com.example.backend_ecommerce_milk_tea.services.OrderDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orderDetails")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    // Thêm chi tiết đơn hàng mới
    @PostMapping("createOrderDetail")
    public ResponseEntity<?> addOrderDetail(@Valid @RequestBody OrderDetailDTO orderDetailDTO, BindingResult result) {
        try{
            if(result.hasErrors()){
                List<String> errors = result.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errors);
            }
            OrderDetails newOrderDetail = orderDetailService.addOrderDetail(orderDetailDTO);
            return new ResponseEntity<>(newOrderDetail, HttpStatus.CREATED);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Lấy danh sách chi tiết đơn hàng theo Order ID
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderDetailsByOrderId(@PathVariable("orderId") Long orderId) throws DataNotFoundException {
        try {
            List<OrderDetails> orderDetails = orderDetailService.getOrderDetailsByOrderId(orderId);
            return new ResponseEntity<>(orderDetails, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Cập nhật chi tiết đơn hàng
    @PutMapping("update/{orderDetailId}")
    public ResponseEntity<?> updateOrderDetail(@PathVariable Long orderDetailId,@Valid @RequestBody OrderDetailDTO orderDetailDTO) {
        try{
            OrderDetails updatedOrderDetail = orderDetailService.updateOrderDetail(orderDetailId, orderDetailDTO);
            return new ResponseEntity<>(updatedOrderDetail, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Xoá chi tiết đơn hàng
    @DeleteMapping("delete/{orderDetailId}")
    public ResponseEntity<Void> removeOrderDetail(@PathVariable Long orderDetailId) {
        orderDetailService.removeOrderDetail(orderDetailId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
