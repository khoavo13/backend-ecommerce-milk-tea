package com.example.backend_ecommerce_milk_tea.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImageDTO {
    @JsonProperty("products_id")
    private Long productsId;
    @Size(min=5,max = 200, message = "ten cua hinh anh tu 5 den 200")
    @JsonProperty("image_url")
    private String imageUrl;
}
