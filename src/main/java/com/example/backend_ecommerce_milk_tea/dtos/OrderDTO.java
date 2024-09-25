package com.example.backend_ecommerce_milk_tea.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @Min(value=1, message = "User ID must be > 0")
    private Long userId;

    private String fullName;

    private String email;

    @JsonProperty("phone_number")
    @NotEmpty(message = "Phone number doesn't have")
    private String phoneNumber;

    private String address;
    private String note;

    private String status;

    @Min(value  =0, message = "Total money must be > 0")
    private Long totalMoney;

    private String shippingMethod;


    private String shippingAddress;


    private LocalDate shippingDate;


    private String trackingNumber;


    private String paymentMethod;


    private Boolean active;

    private List<OrderDetailDTO> orderDetailsDTO;

}
