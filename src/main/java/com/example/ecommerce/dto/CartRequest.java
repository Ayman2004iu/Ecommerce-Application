package com.example.ecommerce.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {
    private Long productId;
    private int quantity;
}

