package com.example.ecommerce.service;

import com.example.ecommerce.model.User;

public interface UserService {
    User getUserByEmail(String email);
    User getUserById(Long id);
}
