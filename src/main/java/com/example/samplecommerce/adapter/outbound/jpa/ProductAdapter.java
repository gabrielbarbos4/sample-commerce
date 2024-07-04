package com.example.samplecommerce.adapter.outbound.jpa;

import com.example.samplecommerce.application.domain.Product;
import com.example.samplecommerce.application.ports.outbound.ProductOutboundPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductAdapter implements ProductOutboundPort {

    private final ProductRepository repository;

    public ProductAdapter(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> getProductList() {
        return null;
    }

    @Override
    public void deleteProduct(int id) {
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return Optional.empty();
    }
}
