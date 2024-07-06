package com.example.samplecommerce.adapter.outbound.jpa.product;

import com.example.samplecommerce.adapter.outbound.jpa.ProductEntity;
import com.example.samplecommerce.application.domain.Product;

import java.math.BigDecimal;

public class ProductAdapterHelper {
    public static ProductEntity mockEntity() {
        return ProductEntity.builder()
            .id(1L)
            .name("mockProductEntity")
            .price(BigDecimal.valueOf(205.23))
            .quantity(5)
            .available(true)
            .build();
    }

    public static Product getProductByIdReturn() {
        return new Product(
            "mockProductEntity",
            BigDecimal.valueOf(205.23),
            true,
            5
        );
    }
}
