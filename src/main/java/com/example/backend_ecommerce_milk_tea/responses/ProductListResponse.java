package com.example.backend_ecommerce_milk_tea.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductListResponse {
    private List<ProductResponse> productResponseList;
    private int totalPages;
}
