package com.example.backend_ecommerce_milk_tea.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Products extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // khởi tạo giá trị id tự động bắt đầu từ 1
    private Long product_id; //id sản phẩm

    @NotBlank(message = "tên không được để trống")
    @Size(min=5,max=50,message = "tên phải có từ 5 đến 50 ký tự")
    private String product_name; //tên sản phẩm

    @NotBlank(message = "mô tả không được để trống")
    private String product_description; //mô tả sản phẩm


    private double product_price; //giá của sản phẩm
}
