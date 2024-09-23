package com.example.backend_ecommerce_milk_tea.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    @JsonProperty("user_id")
    @Min(value=1, message = "User ID must be > 0")
    private Long userId;

    @JsonProperty("fullname")
    private String fullname;

    private String email;

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number doesn't have")
    private String phoneNumber;

    private String address;
    private String note;

    private String status;

    @JsonProperty("totoal_money")
    @Min(value  =0, message = "Total money must be > 0")
    private Long totalMoney;

    @JsonProperty("shipping_method")
    private String shippingMethod;

    @JsonProperty("shipping_address")
    private String shippingAddress;

    @JsonProperty("shipping_date")
    private LocalDate shippingDate;

    @JsonProperty("tracking_number")
    private String trackingNumber;

    @JsonProperty("payment_method")
    private String paymentMethod;

    @JsonProperty("active")
    private Boolean active;

    private List<OrderDetailDTO> orderDetailsDTO;
}
