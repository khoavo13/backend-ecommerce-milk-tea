package com.example.backend_ecommerce_milk_tea.services;

import com.example.backend_ecommerce_milk_tea.models.CartItems;
import com.example.backend_ecommerce_milk_tea.models.Carts;
import com.example.backend_ecommerce_milk_tea.models.Products;
import com.example.backend_ecommerce_milk_tea.repositories.CartItemRepository;
import com.example.backend_ecommerce_milk_tea.repositories.CartRepository;
import com.example.backend_ecommerce_milk_tea.repositories.ProductRepository;
import com.example.backend_ecommerce_milk_tea.repositories.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService{
    private final CartRepository cartRepository;
    @Getter
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public Carts getCartByUser(Long userId) {
        return cartRepository.findByUser_UserId(userId).orElse(null);
    }


    @Override
    public Carts addToCart(Long userId, Long productId, int quantity) {
        Carts cart = cartRepository.findByUser_UserId(userId)
                .orElseGet(() -> {
                    Carts newCart = new Carts();
                    newCart.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
                    newCart.setTotalPrice(0.0);
                    return cartRepository.save(newCart);
                });

        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItems cartItem = cart.getItems().stream()
                .filter(item -> item.getProducts().getId().equals(productId))
                .findFirst()
                .orElse(new CartItems());

        cartItem.setProducts(product);
        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItem.setCart(cart);

        if (!cart.getItems().contains(cartItem)) {
            cart.getItems().add(cartItem);
        }

        updateCartTotalPrice(cart);
        return cartRepository.save(cart);
    }

    @Override
    public Carts updateCartItem(Long userId, Long productId, int quantity) {
        Carts cart = getCartByUser(userId);

        CartItems cartItem = cart.getItems().stream()
                .filter(item -> item.getProducts().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        cartItem.setQuantity(quantity);
        updateCartTotalPrice(cart);

        return cartRepository.save(cart);
    }

    @Override
    public Carts removeFromCart(Long userId, Long productId) {
        Carts cart = getCartByUser(userId);

        cart.getItems().removeIf(item -> item.getProducts().getId().equals(productId));
        updateCartTotalPrice(cart);

        return cartRepository.save(cart);
    }

    @Override
    public void updateCartTotalPrice(Carts cart) {
        double total = cart.getItems().stream()
                .mapToDouble(item -> item.getProducts().getProductPrice() * item.getQuantity())
                .sum();
        cart.setTotalPrice(total);
    }

    @Override
    public List<CartItems> getItemsByCartId(Long cartId) {
        return cartItemRepository.findByCart_Id(cartId);
    }

}
