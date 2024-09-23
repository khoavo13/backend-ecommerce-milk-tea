package com.example.backend_ecommerce_milk_tea.controllers;

import com.example.backend_ecommerce_milk_tea.dtos.CategoryDTO;
import com.example.backend_ecommerce_milk_tea.dtos.ProductDTO;
import com.example.backend_ecommerce_milk_tea.models.Categories;
import com.example.backend_ecommerce_milk_tea.models.Products;
import com.example.backend_ecommerce_milk_tea.responses.ApiResponse;
import com.example.backend_ecommerce_milk_tea.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    private final ProductService productService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllProducts() {
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(productService.getAllProducts())
                .status(HttpStatus.OK.value())
                .message("OK")
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        Products products = productService.getProductById(id);
        if (products == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        productService.deleteProduct(id);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(null)
                .message("Deleted product Successfully")
                .status(HttpStatus.NO_CONTENT.value())
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
            ApiResponse apiResponse = ApiResponse
                    .builder()
                    .data(errors)
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("validation failed")
                    .build();
            return ResponseEntity.badRequest().body(apiResponse);
        }
        Products products = productService.updateProduct(id, productDTO);
        if(products == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(products)
                .status(HttpStatus.OK.value())
                .message("Update Product Success")
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
}