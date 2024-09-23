package com.example.backend_ecommerce_milk_tea.controllers;

import com.example.backend_ecommerce_milk_tea.dtos.CategoryDTO;
import com.example.backend_ecommerce_milk_tea.dtos.ProductDTO;
import com.example.backend_ecommerce_milk_tea.dtos.ProductImageDTO;
import com.example.backend_ecommerce_milk_tea.exceptions.ResourceNotFoundException;
import com.example.backend_ecommerce_milk_tea.models.Categories;
import com.example.backend_ecommerce_milk_tea.models.ProductImage;
import com.example.backend_ecommerce_milk_tea.models.Products;
import com.example.backend_ecommerce_milk_tea.responses.ApiResponse;
import com.example.backend_ecommerce_milk_tea.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
                .message("Deleted Successfully")
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
                .message("Update Success")
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchProduct(
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "priceMin", required = false) Double priceMin,
            @RequestParam(value = "priceMax", required = false) Double priceMax) {
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(productService.searchProducts(productName, priceMin, priceMax))
                .message("Search Success")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getAllImage/{id}")
    public ResponseEntity<ApiResponse> getAllImage(@PathVariable Long id) {
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(productService.getProductImages(id))
                .status(HttpStatus.OK.value())
                .message("Get All Image Success")
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/uploads/{id}")
    public ResponseEntity<ApiResponse>  uploads(@PathVariable Long id, @ModelAttribute("files") List<MultipartFile> files) throws IOException {
        List<ProductImage> productImages = new ArrayList<>();
        int count = 0;
        for (MultipartFile file : files) {
            if (file != null) {
                if (file.getSize() == 0){
                    count++;
                    continue;
                }
            }
            String fileName = storeFile(file);
            ProductImageDTO productImageDTO = ProductImageDTO.builder()
                    .imageUrl(fileName)
                    .build();
            ProductImage productImage = productService.addProductImage(id, productImageDTO);
            if (productImage.getProducts() == null) {
                throw new ResourceNotFoundException("Product khong tim thay voi id: " + id);
            }
            productImages.add(productImage);
        }


        ApiResponse apiResponse = ApiResponse.builder()
                .data(productImages)
                .message("Upload images successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/images/{imageName}")
    public ResponseEntity<?> getImage(@PathVariable String imageName) {
        try{
            java.nio.file.Path imagePath = Paths.get("upload/"+imageName);
            UrlResource resource = new UrlResource(imagePath.toUri());
            if(resource.exists()){
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
            }else{
                return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(new UrlResource(Paths.get("upload/notfound.jpeg").toUri()));
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    private String storeFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFileName = UUID.randomUUID().toString()+"_"+fileName;
        java.nio.file.Path uploadDdir= Paths.get("upload");
        if(!Files.exists(uploadDdir)) {
            Files.createDirectory(uploadDdir);
        }
        java.nio.file.Path destination=Paths.get(uploadDdir.toString(),uniqueFileName);
        Files.copy(file.getInputStream(),destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }

    @DeleteMapping("/images/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProductImages(@PathVariable Long id){
        ProductImage productImage = productService.getProductImageById(id);
        if (productImage == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "image not found");
        }
        productService.deleteProductImage(id);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(null)
                .message("Deleted Successfully")
                .status(HttpStatus.NO_CONTENT.value())
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
}
