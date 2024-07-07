package com.example.samplecommerce.application.ports.outbound;

import com.example.samplecommerce.application.domain.Product;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface ProductOutboundPort {
    Product saveProduct(Product product);

    List<Product> getProductList();

    Product getProductById(Long id) throws EntityNotFoundException;
}
