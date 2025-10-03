package com.example.ecommerce.service;


import com.example.ecommerce.dto.AuthResponses;
import com.example.ecommerce.dto.LoginRequest;
import com.example.ecommerce.dto.RegisterRequest;


public interface AuthService {
    AuthResponses register(RegisterRequest request);
    AuthResponses login(LoginRequest request);
}
