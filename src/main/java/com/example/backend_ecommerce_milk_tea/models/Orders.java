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
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Users users;

    @Column(name="fullname", length = 100,nullable = false)
    private String fullName;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(name="phone_number", length = 20, nullable = false)
    private String phoneNumber;

    @Column(length = 255,nullable = false)
    private String address;

    @Column(length = 255)
    private String note;

    @Column(nullable = false)
    private String status;

    @Column(name = "total_money", nullable = false)
    private Long totalMoney;

    @Column(name = "shipping_method", nullable = false)
    private String shippingMethod;

    @Column(name="shipping_address", nullable = false)
    private String shippingAddress;

    @Column(name="shipping_date")
    private LocalDate shippingDate;

    @Column(name="tracking_number", length = 50)
    private String trackingNumber;

    @Column(name="payment_method", nullable = false)
    private String paymentMethod;

    @Column(name="active", nullable = false)
    private Boolean  active; //Mặc định là true (đơn hàng đang hoạt động)


}
