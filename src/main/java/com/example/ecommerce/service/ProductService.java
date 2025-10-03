package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductRequest;
import com.example.ecommerce.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    List<ProductResponse> getProducts(String name, Long categoryId);
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct(Long id);
}
