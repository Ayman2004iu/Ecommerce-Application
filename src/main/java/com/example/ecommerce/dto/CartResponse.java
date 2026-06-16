package com.example.ecommerce.dto;

import lombok.*;
import java.math.BigDecimal;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponse {
    private Long id;
    private Long userId;
    @Builder.Default
    private List<CartItemResponse> items= List.of();
    private BigDecimal totalPrice;

}
