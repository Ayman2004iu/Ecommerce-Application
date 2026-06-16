package com.example.ecommerce.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    @NotBlank(message = "Shipping address is required")
    @Size(min = 10, max = 255, message = "Shipping address must be between 10 and 255 characters")
    private String shippingAddress;

    @NotBlank(message = "Payment method is required")
    @Size(min = 2, max = 50, message = "Payment method must be between 2 and 50 characters")
    private String paymentMethod;
}
