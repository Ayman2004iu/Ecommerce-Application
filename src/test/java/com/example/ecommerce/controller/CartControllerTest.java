package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CartRequest;
import com.example.ecommerce.dto.CartResponse;
import com.example.ecommerce.model.User;
import com.example.ecommerce.security.CustomUserDetailsService;
import com.example.ecommerce.security.JwtUtil;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CartController.class)
@ImportAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
})
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CartService cartService;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;

    @MockitoBean
    private JwtUtil jwtUtil;

    @Test
    void addProductToCart_Success() throws Exception {
        User mockUser = new User();
        mockUser.setId(1L);

        CartRequest request = new CartRequest(1L, 2);
        CartResponse response = new CartResponse(1L, 1L, List.of(), BigDecimal.ZERO);

        when(userService.getUserByEmail(any())).thenReturn(mockUser);
        when(cartService.addProductToCart(any(), any(CartRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/cart")
                        .with(user("testuser").roles("USER"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void getUserCart_Success() throws Exception {
        User mockUser = new User();
        mockUser.setId(1L);

        CartResponse response = new CartResponse(1L, 1L, List.of(), BigDecimal.ZERO);

        when(userService.getUserByEmail(any())).thenReturn(mockUser);
        when(cartService.getUserCart(any())).thenReturn(response);

        mockMvc.perform(get("/api/cart")
                        .with(user("testuser").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }
}