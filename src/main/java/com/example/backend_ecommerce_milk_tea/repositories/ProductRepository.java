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
    @Query("select p from Products p where p.category.categoryId = :id")
    List<Products> findByCategoryId(Long  id);

    @Query("select p from Products p where "+
    "(:productName is null or p.productName like %:productName%) and "+
    "(:priceMin is null or p.productPrice >= :priceMin) and "+
    "(:priceMax is null or p.productPrice <= :priceMax)")
    List<Products> searchProducts(
        @Param("productName") String productName,
        @Param("priceMin") Double priceMin,
        @Param("priceMax") Double priceMax
    );
}
