package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CartRequest;
import com.example.ecommerce.dto.CartResponse;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<CartResponse> addProduct(@AuthenticationPrincipal String username, @Valid @RequestBody CartRequest request) {
        User user = userService.getUserByEmail(username);
        return ResponseEntity.ok(cartService.addProductToCart(user.getId(), request));
    }

    @GetMapping
    public ResponseEntity<CartResponse> getCart(@AuthenticationPrincipal String username) {
        User user = userService.getUserByEmail(username);
        return ResponseEntity.ok(cartService.getUserCart(user.getId()));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<CartResponse> removeProduct(@AuthenticationPrincipal String username, @PathVariable Long productId) {
        User user = userService.getUserByEmail(username);
        return ResponseEntity.ok(cartService.removeProductFromCart(user.getId(), productId));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(@AuthenticationPrincipal String username) {
        User user = userService.getUserByEmail(username);
        cartService.clearCart(user.getId());
        return ResponseEntity.noContent().build();
    }
}

