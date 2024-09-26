package com.example.backend_ecommerce_milk_tea.services;

import com.example.backend_ecommerce_milk_tea.dtos.CartItemDto;
import com.example.backend_ecommerce_milk_tea.models.CartItems;
import com.example.backend_ecommerce_milk_tea.models.Carts;

public interface ICartService {

    // Lấy giỏ hàng của người dùng
    Carts getCartByUserId(Long userId);

    // Thêm sản phẩm vào giỏ hàng
    CartItems addProductToCart(CartItemDto cartItemDto);

    // Cập nhật giỏ hàng (thay đổi số lượng sản phẩm)
    CartItems updateCartItem(CartItems cartItem);

    // Xóa giỏ hàng
    void deleteCartByUserId(Long userId);
}
