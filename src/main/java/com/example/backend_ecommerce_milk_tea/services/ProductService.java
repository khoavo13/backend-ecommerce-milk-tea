package com.example.backend_ecommerce_milk_tea.services;


import com.example.backend_ecommerce_milk_tea.dtos.ProductDTO;
import com.example.backend_ecommerce_milk_tea.dtos.ProductImageDTO;
import com.example.backend_ecommerce_milk_tea.models.ProductImage;
import com.example.backend_ecommerce_milk_tea.models.Products;
import com.example.backend_ecommerce_milk_tea.repositories.ProductImageResponse;
import com.example.backend_ecommerce_milk_tea.repositories.ProductRepository;
import com.example.backend_ecommerce_milk_tea.responses.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductImageResponse productImageResponse;
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
        return productRepository.findAll(pageable).map(products -> {
            return ProductResponse.fromProduct(products);
        });
    }

    @Override
    public List<Products> searchProducts(String productName, Double priceMin, Double priceMax) {
        return productRepository.searchProducts(productName, priceMin, priceMax);
    }

    @Override
    public Products updateProduct(Long productId, ProductDTO productDTO) {
        Products product = getProductById(productId);
        product.setProductName(productDTO.getProductName());
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductPrice(productDTO.getProductPrice());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductImage> getProductImages(Long productId) {
        return productImageResponse.findByProductId(productId);
    }

    @Override
    public ProductImage addProductImage(Long productId, ProductImageDTO productImageDTO) {
        Products products = getProductById(productId);
        ProductImage productImage = ProductImage
                .builder()
                .products(products)
                .imageUrl(productImageDTO.getImageUrl())
                .build();
        return productImageResponse.save(productImage);
    }

    @Override
    public ProductImage getProductImageById(Long productImageId) {
        return productImageResponse.findById(productImageId).orElse(null);
    }

    @Override
    public void deleteProductImage(Long productImageId) {
        productImageResponse.deleteById(productImageId);
    }
}
