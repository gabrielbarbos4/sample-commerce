package com.example.samplecommerce.application.ports.inbound;

import com.example.samplecommerce.application.domain.Product;

import java.util.List;

public interface ProductInboundPort {
    Product createProduct(Product product);
    Product getProductById(Long id);
    List<Product> getProductList();
}
