package com.example.backend_ecommerce_milk_tea.repositories;

import com.example.backend_ecommerce_milk_tea.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductImageResponse extends JpaRepository<ProductImage, Long> {
    @Query("select p from ProductImage p where p.products.id = :productId")
    List<ProductImage> findByProductId(Long productId);
}
