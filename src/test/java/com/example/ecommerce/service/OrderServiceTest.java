package com.example.ecommerce.service;

import com.example.ecommerce.dto.OrderItemResponse;
import com.example.ecommerce.dto.OrderRequest;
import com.example.ecommerce.dto.OrderResponse;
import com.example.ecommerce.exception.BusinessException;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.mapper.OrderMapper;
import com.example.ecommerce.model.*;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    private User user;
    private Cart cart;
    private Product product;
    private CartItem cartItem;
    private OrderRequest orderRequest;
    private Order order;
    private OrderResponse orderResponse;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .username("testuser")
                .email("test@example.com")
                .password("password")
                .roles(Set.of(Role.USER))
                .build();

        product = Product.builder()
                .id(1L)
                .name("Laptop")
                .price(new BigDecimal("999.99"))
                .quantity(10)
                .build();

        cartItem = CartItem.builder()
                .id(1L)
                .product(product)
                .quantity(2)
                .price(new BigDecimal("999.99"))
                .build();

        cart = Cart.builder()
                .id(1L)
                .user(user)
                .items(new java.util.HashSet<>(java.util.List.of(cartItem)))
                .build();

        orderRequest = OrderRequest.builder()
                .shippingAddress("123 Main St")
                .paymentMethod("Credit Card")
                .build();

        order = Order.builder()
                .id(1L)
                .user(user)
                .shippingAddress("123 Main St")
                .paymentMethod("Credit Card")
                .status(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .totalPrice(new BigDecimal("1999.98"))
                .items(new java.util.ArrayList<>())
                .build();

        orderResponse = OrderResponse.builder()
                .id(1L)
                .username("testuser")
                .shippingAddress("123 Main St")
                .paymentMethod("Credit Card")
                .status("PENDING")
                .totalPrice(new BigDecimal("1999.98"))
                .items(List.of())
                .build();
    }

    @Test
    void createOrder_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        when(orderMapper.toResponse(order)).thenReturn(orderResponse);

        OrderResponse result = orderService.createOrder(1L, orderRequest);

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void createOrder_CartEmpty() {
        cart.setItems(new java.util.HashSet<>());
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));

        assertThrows(BusinessException.class, () -> orderService.createOrder(1L, orderRequest));
    }

    @Test
    void createOrder_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> orderService.createOrder(1L, orderRequest));
    }

    @Test
    void getUserOrders_Success() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Order> orderPage = new PageImpl<>(List.of(order));

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(orderRepository.findByUser(user, pageable)).thenReturn(orderPage);
        when(orderMapper.toResponse(order)).thenReturn(orderResponse);

        Page<OrderResponse> result = orderService.getUserOrders(1L, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void getAllOrders_Success() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Order> orderPage = new PageImpl<>(List.of(order));

        when(orderRepository.findAll(pageable)).thenReturn(orderPage);
        when(orderMapper.toResponse(order)).thenReturn(orderResponse);

        Page<OrderResponse> result = orderService.getAllOrders(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }
}
