package com.example.backend_ecommerce_milk_tea.services;

import com.example.backend_ecommerce_milk_tea.models.Products;

public interface IProductService {
    Products getProductById(Long id);
    Products addProduct(Products product);
    Products updateProduct(Long id,Products product);
    void deleteProduct(Long id);

}
