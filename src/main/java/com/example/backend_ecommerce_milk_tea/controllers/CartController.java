package com.example.backend_ecommerce_milk_tea.controllers;

import com.example.backend_ecommerce_milk_tea.models.Carts;
import com.example.backend_ecommerce_milk_tea.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    // Xem giỏ hàng của người dùng
    @GetMapping("/{userId}")
    public ResponseEntity<Carts> getCart(@PathVariable Long userId) {
        Carts cart = cartService.getCartByUser(userId);
        return ResponseEntity.ok(cart);
    }

    // Thêm sản phẩm vào giỏ hàng
    @PostMapping("/{userId}/add")
    public ResponseEntity<Carts> addToCart(@PathVariable Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        Carts cart = cartService.addToCart(userId, productId, quantity);
        return ResponseEntity.ok(cart);
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    @PutMapping("/{userId}/update")
    public ResponseEntity<Carts> updateCartItem(@PathVariable Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        Carts cart = cartService.updateCartItem(userId, productId, quantity);
        return ResponseEntity.ok(cart);
    }

    // Xóa sản phẩm khỏi giỏ hàng
    @DeleteMapping("/{userId}/remove")
    public ResponseEntity<Carts> removeFromCart(@PathVariable Long userId, @RequestParam Long productId) {
        Carts cart = cartService.removeFromCart(userId, productId);
        return ResponseEntity.ok(cart);
    }
}
