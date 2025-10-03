package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CartRequest;
import com.example.ecommerce.dto.CartResponse;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.CartService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final UserRepository userRepository;

    public CartController(CartService cartService , UserRepository userRepository) {
        this.cartService = cartService;
        this.userRepository = userRepository;
    }


    @PostMapping
    public ResponseEntity<CartResponse> addProduct( Authentication auth,@Valid @RequestBody CartRequest request) {
        User user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("user not found"));
        return ResponseEntity.ok(cartService.addProductToCart(user.getId(), request));
    }

    @GetMapping
    public ResponseEntity<CartResponse> getCart(Authentication auth) {
        User user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("user not found"));
        return ResponseEntity.ok(cartService.getUserCart(user.getId()));
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<CartResponse> removeProduct(Authentication auth, @PathVariable Long productId) {
        User user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(cartService.removeProductFromCart(user.getId(), productId));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(Authentication auth) {
        User user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        cartService.clearCart(user.getId());
        return ResponseEntity.noContent().build();
    }
}

