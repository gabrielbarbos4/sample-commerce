package com.example.samplecommerce.application.ports.outbound;

import com.example.samplecommerce.application.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductOutboundPort {
    Product saveProduct(Product product);
    List<Product> getProductList();
    Optional<Product> getProductById(Long id);
}
