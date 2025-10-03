package com.example.ecommerce.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponses {
    private String token;
    private String username;
    private String email;
}
