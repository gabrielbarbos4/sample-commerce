package com.example.samplecommerce.application.ports.inbound;

import com.example.samplecommerce.application.domain.PageableProduct;
import com.example.samplecommerce.application.domain.Product;

public interface ProductInboundPort {
    Product createProduct(Product product);

    Product getProductById(Long id);

    PageableProduct getProductList(int page, int pageSize);
}
