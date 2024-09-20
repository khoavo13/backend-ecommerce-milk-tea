package com.example.backend_ecommerce_milk_tea.services;

import com.example.backend_ecommerce_milk_tea.models.Carts;

public interface ICartService {

    // Lấy giỏ hàng của người dùng theo ID
    Carts getCartByUser(Long userId);

    // Thêm sản phẩm vào giỏ hàng
    Carts addToCart(Long userId, Long productId, int quantity);

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    Carts updateCartItem(Long userId, Long productId, int quantity);

    // Xóa sản phẩm khỏi giỏ hàng
    Carts removeFromCart(Long userId, Long productId);

    // Tính tổng giá giỏ hàng
    void updateCartTotalPrice(Carts cart);
}
