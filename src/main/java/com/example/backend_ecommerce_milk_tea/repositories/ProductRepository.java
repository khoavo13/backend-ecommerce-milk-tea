package com.example.backend_ecommerce_milk_tea.repositories;

import com.example.backend_ecommerce_milk_tea.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    @Query("select p from Products p where"+
    "(:productName is null or p.productName like %:productName%) and"+
    "(:productPriceMin is null or p.productPrice >= :productPriceMin) and"+
    "(:productPriceMax is null or p.productPrice <= :productPriceMax)")
    List<Products> findByProductNameAndProductPriceBetween(
            @Param("productName") String productName,
            @Param("productPriceMin") Double productPriceStart,
            @Param("productPriceMax") Double productPriceEnd
    );
}
