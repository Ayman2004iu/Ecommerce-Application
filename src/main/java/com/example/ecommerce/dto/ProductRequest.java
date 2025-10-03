package com.example.ecommerce.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    private String name;
    private BigDecimal price;
    private int quantity;
    private Long categoryId;
    private String description;

}
