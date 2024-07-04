package com.example.samplecommerce.adapter.outbound.jpa;

import com.example.samplecommerce.application.domain.Product;
import com.example.samplecommerce.application.ports.outbound.ProductOutboundPort;
import jakarta.persistence.EntityNotFoundException;
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
        repository.save(new ProductEntity(product));

        return product;
    }

    @Override
    public List<Product> getProductList() {
        return repository.findAll().stream().map(ProductEntity::toDomain).toList();
    }


    @Override
    public Optional<Product> getProductById(Long id) {
        return repository.findById(id).map(ProductEntity::toDomain);
    }
}
