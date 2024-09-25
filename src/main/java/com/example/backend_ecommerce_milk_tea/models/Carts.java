package com.example.backend_ecommerce_milk_tea.models;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carts extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // khởi tạo giá trị id tự động bắt đầu từ 1
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id", nullable = false)
    private Users user;

    private Double totalPrice;

}