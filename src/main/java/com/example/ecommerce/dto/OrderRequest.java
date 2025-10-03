package com.example.ecommerce.dto;

import com.example.ecommerce.model.OrderStatus;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    private String shippingAddress;
    private String paymentMethod;
    private OrderStatus status;
}
