package com.example.backend_ecommerce_milk_tea.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="tokens")
@Builder
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="token",length=255)
    private String token;

    @Column(name="refresh_token",length=255)
    private String refreshToken;


    @Column(name="token_type",length=50)
    private String tokenType;

    @Column(name="expiration_date")
    private LocalDateTime expirationDate;

    @Column(name="refresh_expiration_date")
    private LocalDateTime refreshExpirationDate;

    private boolean revoked;
    private boolean expired;

    @Column(name="is_mobile",columnDefinition = "TINYINT(1)")
    private  boolean isMobile;


    @ManyToOne
    @JoinColumn(name="user_id")
    private Users user;
}
