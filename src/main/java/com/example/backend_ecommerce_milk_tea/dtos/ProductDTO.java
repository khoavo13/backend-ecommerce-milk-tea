package com.example.backend_ecommerce_milk_tea.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @JsonProperty("category_id")
    private Long categoryId; //id category

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(min=5,max=50,message = "tên phải có từ 5 đến 50 ký tự")
    private String productName; // tên sản phẩm

    @NotBlank(message = "mô tả không được để trống")
    private String productDescription; // mô tả sản phẩm

    private Double productPrice; // giá của sản phẩm
}
