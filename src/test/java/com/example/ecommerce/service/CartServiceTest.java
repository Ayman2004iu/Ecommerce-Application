package com.example.ecommerce.service;

import com.example.ecommerce.dto.CartItemResponse;
import com.example.ecommerce.dto.CartRequest;
import com.example.ecommerce.dto.CartResponse;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.mapper.CartMapper;
import com.example.ecommerce.model.*;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.impl.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CartMapper cartMapper;

    @InjectMocks
    private CartServiceImpl cartService;

    private User user;
    private Cart cart;
    private Product product;
    private CartRequest cartRequest;
    private CartResponse cartResponse;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .username("testuser")
                .email("test@example.com")
                .password("password")
                .roles(new HashSet<>())
                .build();

        product = Product.builder()
                .id(1L)
                .name("Laptop")
                .price(new BigDecimal("999.99"))
                .quantity(10)
                .build();

        cart = Cart.builder()
                .id(1L)
                .user(user)
                .items(new HashSet<>())
                .build();

        cartRequest = new CartRequest(1L, 2);

        cartResponse = CartResponse.builder()
                .id(1L)
                .userId(1L)
                .items(List.of())
                .build();
    }

    @Test
    void addProductToCart_NewCart_Success() {
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.empty());
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(cartMapper.toResponse(any(Cart.class))).thenReturn(cartResponse);

        CartResponse result = cartService.addProductToCart(1L, cartRequest);

        assertNotNull(result);
        verify(cartRepository, times(2)).save(any(Cart.class));
    }

    @Test
    void addProductToCart_ExistingCart_Success() {
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        when(cartMapper.toResponse(any(Cart.class))).thenReturn(cartResponse);

        CartResponse result = cartService.addProductToCart(1L, cartRequest);

        assertNotNull(result);
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    void addProductToCart_ProductNotFound() {
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> cartService.addProductToCart(1L, cartRequest));
    }

    @Test
    void getUserCart_Success() {
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));
        when(cartMapper.toResponse(cart)).thenReturn(cartResponse);

        CartResponse result = cartService.getUserCart(1L);

        assertNotNull(result);
    }

    @Test
    void getUserCart_CartNotFound() {
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> cartService.getUserCart(1L));
    }

    @Test
    void removeProductFromCart_Success() {
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));
        when(cartMapper.toResponse(cart)).thenReturn(cartResponse);

        CartResponse result = cartService.removeProductFromCart(1L, 1L);

        assertNotNull(result);
    }

    @Test
    void clearCart_Success() {
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        cartService.clearCart(1L);

        verify(cartRepository, times(1)).save(any(Cart.class));
    }
}
