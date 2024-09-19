package com.example.backend_ecommerce_milk_tea.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // khởi tạo giá trị id tự động bắt đầu từ 1
    private Long orderId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Users users;

    @Column(name="fullname", length = 100)
    private String fullName;

    @Column(length = 100)
    private String email;

    @Column(name="phone_number", length = 100)
    private String phoneNumber;

    @Column(length = 100)
    private String address;

    @Column(length = 100)
    private String note;

    @Column(name = "order_date")
    private Date orderDate;

    private String status;

    @Column(name = "total_money")
    private Long totalMoney;

    @Column(name = "shipping_method")
    private String shippingMethod;

    @Column(name="shipping_address")
    private String shippingAddress;

    @Column(name="shipping_date")
    private LocalDate shippingDate;

    @Column(name="tracking_number")
    private String trackingNumber;

    @Column(name="payment_method")
    private String paymentMethod;

    @Column(name="active")
    private Boolean  active;

}
