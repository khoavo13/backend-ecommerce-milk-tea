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
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;


    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    private Products product;

    @Column(nullable = false)
    private Long price;

    @Column(name = "number_of_products", nullable = false)
    private int numberOfProducts;

    @Column(name="total_money", nullable = false)
    private Long totalMoney;

    @Column(name="size", length = 50, nullable = false)
    private String size; // Thêm kích thước (small, medium, large)


}