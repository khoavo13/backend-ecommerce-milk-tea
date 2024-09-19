package com.example.backend_ecommerce_milk_tea.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse extends BaseResponse{
    private int status;
    private String message;
    private Object data;
}