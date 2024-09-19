package com.example.backend_ecommerce_milk_tea.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "order_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetails extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // khởi tạo giá trị id tự động bắt đầu từ 1
    private Long orderDetailId;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Products product;

    @Column(nullable = false)
    private Long price;

    @Column(name = "number_of_products", nullable = false)
    private Integer numberOfProducts;

    @Column(name="total_money", nullable = false)
    private Long totalMoney;

}