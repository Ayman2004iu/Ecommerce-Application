package com.example.ecommerce.controller;

import com.example.ecommerce.dto.OrderRequest;
import com.example.ecommerce.dto.OrderResponse;
import com.example.ecommerce.model.User;
import com.example.ecommerce.security.CustomUserDetailsService;
import com.example.ecommerce.security.JwtUtil;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
@ImportAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
})
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private OrderService orderService;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;

    @MockitoBean
    private JwtUtil jwtUtil;

    @Test
    void createOrder_Success() throws Exception {
        User mockUser = new User();
        mockUser.setId(1L);

        OrderRequest request = new OrderRequest("123 Main St, New York, NY", "Credit Card");
        OrderResponse response = new OrderResponse(1L, "testuser", List.of(), "123 Main St",
                "Credit Card", LocalDateTime.now(), "PENDING", new BigDecimal("999.99"));

        when(userService.getUserByEmail(any())).thenReturn(mockUser);
        when(orderService.createOrder(any(), any(OrderRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/orders")
                        .with(user("testuser").roles("USER"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void getUserOrders_Success() throws Exception {
        User mockUser = new User();
        mockUser.setId(1L);

        OrderResponse response = new OrderResponse(1L, "testuser", List.of(), "123 Main St",
                "Credit Card", LocalDateTime.now(), "PENDING", new BigDecimal("999.99"));
        Page<OrderResponse> page = new PageImpl<>(List.of(response));

        when(userService.getUserByEmail(any())).thenReturn(mockUser);
        when(orderService.getUserOrders(any(), any())).thenReturn(page);

        mockMvc.perform(get("/api/orders")
                        .with(user("testuser").roles("USER")))
                .andExpect(status().isOk());
    }

    @Test
    void getAllOrders_Success() throws Exception {
        OrderResponse response = new OrderResponse(1L, "testuser", List.of(), "123 Main St",
                "Credit Card", LocalDateTime.now(), "PENDING", new BigDecimal("999.99"));
        Page<OrderResponse> page = new PageImpl<>(List.of(response));

        when(orderService.getAllOrders(any())).thenReturn(page);

        mockMvc.perform(get("/api/orders/all")
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk());
    }
}