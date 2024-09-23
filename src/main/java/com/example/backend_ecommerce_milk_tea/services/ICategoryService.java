package com.example.backend_ecommerce_milk_tea.services;

import com.example.backend_ecommerce_milk_tea.dtos.CategoryDTO;
import com.example.backend_ecommerce_milk_tea.dtos.ProductDTO;
import com.example.backend_ecommerce_milk_tea.models.Categories;
import com.example.backend_ecommerce_milk_tea.models.Products;

import java.util.List;

public interface ICategoryService {
    Categories getCategoryById(Long id);
    Categories addCategory(CategoryDTO categoryDTO);
    Categories updateCategory(Long categoryId,CategoryDTO categoryDTO);
    void deleteCategory(Long categoryId);
    List<Categories> getAllCategories();
    Products addProduct(Long categoryId, ProductDTO productDTO);
    List<Products> getProducts(Long id);
}
