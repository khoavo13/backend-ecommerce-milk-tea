package com.example.backend_ecommerce_milk_tea.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    @JsonProperty("order_id")
    @Min(value=1, message = "Order ID must be > 0")
    private Long orderId;

    @JsonProperty("product_id")
    @Min(value=1, message = "Product ID must be > 0")
    private Long productId;

    private String productName;

    private Long price;

    @JsonProperty("number_of_products")
    @Min(value=1, message = "number_of_products must be > 0")
    private Integer numberOfProducts;

    @JsonProperty("total_money")
    @Min(value =0, message = "total money must be >= 0")
    private Long totalMoney;

    @JsonProperty("size")
    private String size;

}
