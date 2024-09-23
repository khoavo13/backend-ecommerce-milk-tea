package com.example.backend_ecommerce_milk_tea.services;

import com.example.backend_ecommerce_milk_tea.dtos.ProductDTO;
import com.example.backend_ecommerce_milk_tea.models.Products;
import com.example.backend_ecommerce_milk_tea.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Products getProductById(Long id);
    List<Products> getAllProducts();
    Page<ProductResponse> getAllProductsPage(Pageable pageable);
    List<Products> searchProducts(String productName, Double priceMin, Double priceMax);
    Products updateProduct(Long productId,ProductDTO productDTO);
    void deleteProduct(Long productId);
}
