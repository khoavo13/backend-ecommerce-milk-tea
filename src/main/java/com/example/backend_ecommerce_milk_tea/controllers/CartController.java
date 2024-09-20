package com.example.backend_ecommerce_milk_tea.controllers;

import com.example.backend_ecommerce_milk_tea.models.CartItems;
import com.example.backend_ecommerce_milk_tea.models.Carts;
import com.example.backend_ecommerce_milk_tea.responses.ApiResponse; // Ensure ApiResponse is imported
import com.example.backend_ecommerce_milk_tea.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    // View user's cart
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getCart(@PathVariable Long userId) {
        Carts cart = cartService.getCartByUser(userId);
        ApiResponse apiResponse;

        if (cart == null) {
            apiResponse = ApiResponse.builder()
                    .data(null)
                    .message("Cart not found for userId: " + userId)
                    .status(HttpStatus.NOT_FOUND.value())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }

        List<CartItems> items = cartService.getItemsByCartId(cart.getId());
        Double totalPrice = items.stream()
                .mapToDouble(item -> item.getProducts().getProductPrice() * item.getQuantity())
                .sum();

        apiResponse = ApiResponse.builder()
                .data(cart) // Directly using Carts here
                .message("Get cart successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    // Add product to cart
    @PostMapping("/{userId}/add")
    public ResponseEntity<ApiResponse> addToCart(@PathVariable Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        ApiResponse apiResponse;

        if (quantity <= 0) {
            apiResponse = ApiResponse.builder()
                    .data(null)
                    .message("Quantity must be greater than zero")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .build();
            return ResponseEntity.badRequest().body(apiResponse);
        }

        try {
            Carts cart = cartService.addToCart(userId, productId, quantity);
            apiResponse = ApiResponse.builder()
                    .data(cart) // Directly using Carts here
                    .message("Product added to cart successfully")
                    .status(HttpStatus.OK.value())
                    .build();
            return ResponseEntity.ok(apiResponse);
        } catch (RuntimeException e) {
            apiResponse = ApiResponse.builder()
                    .data(null)
                    .message(e.getMessage())
                    .status(HttpStatus.NOT_FOUND.value())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    // Update product quantity in cart
    @PutMapping("/{userId}/update")
    public ResponseEntity<ApiResponse> updateCartItem(@PathVariable Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        ApiResponse apiResponse;

        if (quantity <= 0) {
            apiResponse = ApiResponse.builder()
                    .data(null)
                    .message("Quantity must be greater than zero")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .build();
            return ResponseEntity.badRequest().body(apiResponse);
        }

        try {
            Carts cart = cartService.updateCartItem(userId, productId, quantity);
            apiResponse = ApiResponse.builder()
                    .data(cart) // Directly using Carts here
                    .message("Cart item updated successfully")
                    .status(HttpStatus.OK.value())
                    .build();
            return ResponseEntity.ok(apiResponse);
        } catch (RuntimeException e) {
            apiResponse = ApiResponse.builder()
                    .data(null)
                    .message(e.getMessage())
                    .status(HttpStatus.NOT_FOUND.value())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    // Remove product from cart
    @DeleteMapping("/{userId}/remove")
    public ResponseEntity<ApiResponse> removeFromCart(@PathVariable Long userId, @RequestParam Long productId) {
        ApiResponse apiResponse;

        try {
            Carts cart = cartService.removeFromCart(userId, productId);
            apiResponse = ApiResponse.builder()
                    .data(cart) // Directly using Carts here
                    .message("Product removed from cart successfully")
                    .status(HttpStatus.OK.value())
                    .build();
            return ResponseEntity.ok(apiResponse);
        } catch (RuntimeException e) {
            apiResponse = ApiResponse.builder()
                    .data(null)
                    .message(e.getMessage())
                    .status(HttpStatus.NOT_FOUND.value())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }
}
