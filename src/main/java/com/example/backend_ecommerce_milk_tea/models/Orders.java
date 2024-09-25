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
    private Users users;

    @Column( length = 100,nullable = false)
    private String fullName;

    @Column(length = 100, nullable = false)
    private String email;

    @Column( length = 20, nullable = false)
    private String phoneNumber;

    @Column(length = 255,nullable = false)
    private String address;

    @Column(length = 255)
    private String note;

    @Column(nullable = false)
    private String status;

    @Column( nullable = false)
    private Long totalMoney;

    @Column( nullable = false)
    private String shippingMethod;

    @Column( nullable = false)
    private String shippingAddress;


    private LocalDate shippingDate;

    @Column( length = 50)
    private String trackingNumber;

    @Column( nullable = false)
    private String paymentMethod;

    @Column( nullable = false)
    private Boolean  active; //Mặc định là true (đơn hàng đang hoạt động)


}
