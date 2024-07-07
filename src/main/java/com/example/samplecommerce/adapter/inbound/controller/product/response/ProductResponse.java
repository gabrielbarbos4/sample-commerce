package com.example.samplecommerce.adapter.inbound.controller.product.response;

import com.example.samplecommerce.application.domain.Product;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductResponse {

    private final Long id;
    private final String name;
    private final BigDecimal price;
    private final Integer quantity;
    private final boolean available;

    public static ProductResponse fromDomain(Product product) {
        return ProductResponse.builder()
            .id(product.getId())
            .name(product.getName())
            .price(product.getPrice())
            .quantity(product.getQuantity())
            .available(product.isAvailable())
            .build();
    }
}
