package com.example.backend_ecommerce_milk_tea.services;


import com.example.backend_ecommerce_milk_tea.models.Products;
import com.example.backend_ecommerce_milk_tea.repositories.ProductRepository;
import com.example.backend_ecommerce_milk_tea.responses.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    @Override
    public Products getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<ProductResponse> getAllProductsPage(Pageable pageable) {
        return null;
    }

    @Override
    public List<Products> searchProducts(String productName, Double priceMin, Double priceMax) {
        return List.of();
    }
}
