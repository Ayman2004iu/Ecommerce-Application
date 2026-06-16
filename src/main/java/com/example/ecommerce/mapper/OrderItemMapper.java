package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.OrderItemResponse;
import com.example.ecommerce.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "imageUrl", source = "product.imageUrl")
    OrderItemResponse toResponse(OrderItem orderItem);
}
