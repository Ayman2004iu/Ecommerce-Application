package com.example.ecommerce.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private String username;
    private List<OrderItemResponse > items;
    private String shippingAddress;
    private String paymentMethod;
    private LocalDateTime createdAt;
    private String status;
    private BigDecimal totalPrice;

}
