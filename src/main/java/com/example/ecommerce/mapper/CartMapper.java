package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.CartItemResponse;
import com.example.ecommerce.dto.CartResponse;
import com.example.ecommerce.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CartItemMapper.class)
public interface CartMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "totalPrice", ignore = true)
    CartResponse toResponse(Cart cart);
}
