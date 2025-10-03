package com.example.ecommerce.service;

import com.example.ecommerce.dto.OrderRequest;
import com.example.ecommerce.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(Long userId, OrderRequest request);
    List<OrderResponse> getUserOrders(Long userId);
}
