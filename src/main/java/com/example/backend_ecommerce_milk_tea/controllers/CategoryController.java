package com.example.backend_ecommerce_milk_tea.controllers;

import com.example.backend_ecommerce_milk_tea.dtos.CategoryDTO;
import com.example.backend_ecommerce_milk_tea.models.Categories;
import com.example.backend_ecommerce_milk_tea.responses.ApiResponse;
import com.example.backend_ecommerce_milk_tea.services.CategoryService;
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
@RequestMapping("/api/category")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllCategories() {
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(categoryService.getAllCategories())
                .status(HttpStatus.OK.value())
                .message("OK")
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> addCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult bindingResult) {
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
        Categories categories1 = categoryService.addCategory(categoryDTO);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(categories1)
                .status(HttpStatus.OK.value())
                .message("Success")
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        Categories categories = categoryService.getCategoryById(id);
        if (categories == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        categoryService.deleteCategory(id);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(null)
                .message("Deleted Successfully")
                .status(HttpStatus.NO_CONTENT.value())
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id,@Valid @RequestBody CategoryDTO categoryDTO, BindingResult bindingResult) {
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
        Categories categories1 = categoryService.updateCategory(id, categoryDTO);
        if(categories1 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(categories1)
                .status(HttpStatus.OK.value())
                .message("Update Success")
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
}
