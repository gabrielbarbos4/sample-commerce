package com.example.samplecommerce.adapter.inbound.controller.product.response;

import com.example.samplecommerce.application.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponse {

    private final Long id;
    private final String name;
    private final BigDecimal price;
    private final Integer quantity;
    private final boolean available;

    public ProductResponse(Product product) {
        name = product.getName();
        price = product.getPrice();
        quantity = product.getQuantity();
        available = product.isAvailable();
        id = product.getId();
    }
}
