package com.example.backend_ecommerce_milk_tea.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // khởi tạo giá trị id tự động bắt đầu từ 1
    private Long userId;

    @NotBlank(message = "Username không được để trống")
    @Size(min = 2, max=50, message = "Username phải có từ 2 đến 50 ký tự")
    private String username;

    @NotBlank(message = "Password không được để trống")
    private String passwordHash;

    @NotBlank(message = "Email không được để trống")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    private String phone;

    @NotEmpty(message = "Role không được để trống")
    private String role;
}
