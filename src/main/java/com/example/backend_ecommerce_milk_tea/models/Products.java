package com.example.backend_ecommerce_milk_tea.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Products extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // khởi tạo giá trị id tự động bắt đầu từ 1
    private Long id; //id sản phẩm

    @ManyToOne
    @JoinColumn(name="category_id")
    private Categories category;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(min=5,max=50,message = "tên phải có từ 5 đến 50 ký tự")
    private String productName; //tên sản phẩm

    @NotBlank(message = "mô tả không được để trống")
    private String productDescription; //mô tả sản phẩm

    private Double productPrice; //giá của sản phẩm
}
