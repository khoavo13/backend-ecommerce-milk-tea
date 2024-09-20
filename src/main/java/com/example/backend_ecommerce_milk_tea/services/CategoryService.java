package com.example.backend_ecommerce_milk_tea.services;

import com.example.backend_ecommerce_milk_tea.dtos.CategoryDTO;
import com.example.backend_ecommerce_milk_tea.dtos.ProductDTO;
import com.example.backend_ecommerce_milk_tea.models.Categories;
import com.example.backend_ecommerce_milk_tea.models.Products;
import com.example.backend_ecommerce_milk_tea.repositories.CategoryRepository;
import com.example.backend_ecommerce_milk_tea.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    @Override
    public Categories getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Categories addCategory(CategoryDTO categoryDTO) {
        Categories categories = Categories
                .builder()
                .categoryName(categoryDTO.getCategoryName())
                .build();
        return categoryRepository.save(categories);
    }

    @Override
    public Categories updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        Categories categories = getCategoryById(categoryId);
        categories.setCategoryName(categoryDTO.getCategoryName());
        return categoryRepository.save(categories);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<Categories> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Products addProduct(Long categoryId, ProductDTO productDTO) {
        Categories categories = getCategoryById(categoryId);
        Products products = Products
                .builder()
                .category(categories)
                .productName(productDTO.getProductName())
                .productDescription(productDTO.getProductDescription())
                .productPrice(productDTO.getProductPrice())
                .build();
        return productRepository.save(products);
    }

    @Override
    public Products updateProduct(Long categoryId, Long productId, ProductDTO productDTO) {
        return null;
    }

    @Override
    public void deleteProduct(Long categoryId, Long productId) {

    }

    @Override
    public List<Products> getProducts(Long id) {
        return productRepository.findCategoryById(id);
    }
}
