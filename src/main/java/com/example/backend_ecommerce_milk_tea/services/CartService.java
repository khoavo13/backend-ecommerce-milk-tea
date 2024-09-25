package com.example.backend_ecommerce_milk_tea.services;

import com.example.backend_ecommerce_milk_tea.dtos.CartItemDto;
import com.example.backend_ecommerce_milk_tea.models.CartItems;
import com.example.backend_ecommerce_milk_tea.models.Carts;
import com.example.backend_ecommerce_milk_tea.models.Products;
import com.example.backend_ecommerce_milk_tea.models.Users;
import com.example.backend_ecommerce_milk_tea.repositories.CartItemRepository;
import com.example.backend_ecommerce_milk_tea.repositories.CartRepository;
import com.example.backend_ecommerce_milk_tea.repositories.ProductRepository;
import com.example.backend_ecommerce_milk_tea.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService {

    @Autowired
    private CartRepository cartsRepository;

    @Autowired
    private CartItemRepository cartItemsRepository;

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Carts getCartByUserId(Long userId) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return cartsRepository.findByUser(user);
    }

    @Override
    public CartItems addProductToCart(CartItemDto cartItemDto) {
        Products product = productRepository.findById(cartItemDto.getCartId()).orElse(null);
        Carts cart = cartsRepository.findById(cartItemDto.getCartId()).orElse(null);
        CartItems cartItem = CartItems.builder()
                .products(product)
                .cart(cart)
                .quantity(cartItemDto.getQuantity())
                .build();
        return cartItemsRepository.save(cartItem);
    }

    @Override
    public CartItems updateCartItem(CartItems cartItem) {
        return cartItemsRepository.save(cartItem);
    }

    @Override
    public void deleteCartByUserId(Long userId) {
        Carts cart = getCartByUserId(userId);
        cartItemsRepository.deleteAllByCart(cart);
        cartsRepository.delete(cart);
    }
}
