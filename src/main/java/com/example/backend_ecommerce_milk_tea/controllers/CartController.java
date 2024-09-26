package com.example.backend_ecommerce_milk_tea.controllers;

import com.example.backend_ecommerce_milk_tea.dtos.CartItemDto;
import com.example.backend_ecommerce_milk_tea.models.CartItems;
import com.example.backend_ecommerce_milk_tea.models.Carts;
import com.example.backend_ecommerce_milk_tea.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Lấy giỏ hàng của người dùng
    @GetMapping("/{userId}")
    public ResponseEntity<Carts> getCart(@PathVariable Long userId) {
        Carts cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    // Thêm sản phẩm vào giỏ hàng
    @PostMapping("/addCartItem")
    public ResponseEntity<CartItems> addProductToCart(@PathVariable Long userId, @RequestBody CartItemDto cartItem) {
        CartItems addedItem = cartService.addProductToCart(cartItem);
        return ResponseEntity.ok(addedItem);
    }

    // Cập nhật giỏ hàng (thay đổi số lượng sản phẩm)
    @PutMapping("/update")
    public ResponseEntity<CartItems> updateCartItem(@RequestBody CartItems cartItem) {
        CartItems updatedItem = cartService.updateCartItem(cartItem);
        return ResponseEntity.ok(updatedItem);
    }

    // Xóa giỏ hàng
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long userId) {
        cartService.deleteCartByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}
