package com.example.ecommerce.controller;

import com.example.ecommerce.dto.OrderResponse;
import com.example.ecommerce.dto.OrderRequest;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserRepository userRepository;

    public OrderController(OrderService orderService , UserRepository userRepository ) {

        this.orderService = orderService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(Authentication auth, @RequestBody @Valid OrderRequest req) {
        User user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("user not found"));
        OrderResponse order = orderService.createOrder(user.getId(), req);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getUserOrders(Authentication auth) {
        User user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("user not found"));
        List<OrderResponse> orders = orderService.getUserOrders(user.getId());
        return ResponseEntity.ok(orders);
    }
}


