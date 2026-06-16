package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.OrderItemResponse;
import com.example.ecommerce.dto.OrderResponse;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public interface OrderMapper {

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "status", source = "status", qualifiedByName = "orderStatusToString")
    OrderResponse toResponse(Order order);

    @Named("orderStatusToString")
    default String orderStatusToString(com.example.ecommerce.model.OrderStatus status) {
        return status != null ? status.name() : null;
    }
}
