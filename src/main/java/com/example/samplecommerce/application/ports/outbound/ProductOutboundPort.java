package com.example.samplecommerce.application.ports.outbound;

import com.example.samplecommerce.application.domain.PageableProduct;
import com.example.samplecommerce.application.domain.Product;
import jakarta.persistence.EntityNotFoundException;

public interface ProductOutboundPort {
    Product saveProduct(Product product);

    PageableProduct getProductList(int size, int pageSize);

    Product getProductById(Long id) throws EntityNotFoundException;
}
