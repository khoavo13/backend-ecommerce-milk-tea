package com.example.backend_ecommerce_milk_tea.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @CreationTimestamp
    @Column(name="created_ad",updatable = false)
    private LocalDateTime created_ad; //ngày khởi tạo
    @UpdateTimestamp
    @Column(name="updated_ad")
    private LocalDateTime updated_ad; //ngày sửa đổi
}
