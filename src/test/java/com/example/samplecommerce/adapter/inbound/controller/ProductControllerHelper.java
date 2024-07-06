package com.example.samplecommerce.adapter.inbound.controller;

import com.example.samplecommerce.adapter.inbound.controller.product.request.CreateProductRequest;

import java.math.BigDecimal;

public class ProductControllerHelper {
    public static CreateProductRequest validCreateProductRequest() {
        return CreateProductRequest
            .builder()
            .available(true)
            .name("mockProductname")
            .price(BigDecimal.valueOf(235.23))
            .quantity(2)
            .build();
    }
}
