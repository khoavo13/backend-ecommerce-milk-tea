package com.example.backend_ecommerce_milk_tea.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    @NotBlank(message = "Tên danh mục không được để trống")
    private String categoryName; // tên danh mục
}
