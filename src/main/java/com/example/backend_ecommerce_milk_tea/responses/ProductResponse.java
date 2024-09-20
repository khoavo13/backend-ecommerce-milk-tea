package com.example.backend_ecommerce_milk_tea.responses;

import com.example.backend_ecommerce_milk_tea.models.Products;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse extends BaseResponse {
    private Long id;
    private Long categoryId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    public static ProductResponse fromProduct(Products product) {
        ProductResponse productResponse = ProductResponse
                .builder()
                .id(product.getId())
                .categoryId(product.getCategory().getCategoryId())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .build();
        productResponse.setCreatedAt(product.getCreatedAt());
        productResponse.setUpdatedAt(product.getUpdatedAt());
        return productResponse;
    }
}
