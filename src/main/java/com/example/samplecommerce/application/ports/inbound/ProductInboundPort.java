package com.example.samplecommerce.application.ports.inbound;

import com.example.samplecommerce.application.domain.Product;

import java.util.List;

public interface ProductInboundPort {
    Product createProduct(Product product);
    void deleteProduct(int id);
    Product updateProduct(Product product);
    Product getProductById(Long id);
    List<Product> productList();
}
