package com.example.samplecommerce.adapter.inbound.controller.product.response;

import com.example.samplecommerce.application.domain.Product;

import java.math.BigDecimal;

public class GetProductResponse {

    private String name;
    private BigDecimal price;

    public GetProductResponse() { }

    public GetProductResponse(Product product) {
        name = product.getName();
        price = product.getPrice();
    }
}
