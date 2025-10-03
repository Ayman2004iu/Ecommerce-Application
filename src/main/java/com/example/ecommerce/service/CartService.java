package com.example.ecommerce.service;

import com.example.ecommerce.dto.CartRequest;
import com.example.ecommerce.dto.CartResponse;

public interface CartService {
    CartResponse getUserCart(Long userId);
    CartResponse  addProductToCart(Long userId, CartRequest request);
    CartResponse  removeProductFromCart(Long userId, Long productId);
    void clearCart(Long userId);
}

