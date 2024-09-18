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

    @OneToOne
    @JoinColumn(name="order_id")
    private Orders orders;

    @OneToMany
    @JoinColumn(name="product_id")
    private List<Products> products;

    private int quantity; // số lượng sản phẩm
    private Double priceProduct; // giá sản phẩm tại thời điểm đặt hàng
}
