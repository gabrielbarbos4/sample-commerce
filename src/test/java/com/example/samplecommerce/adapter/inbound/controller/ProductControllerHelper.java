package com.example.samplecommerce.adapter.inbound.controller;

import com.example.samplecommerce.adapter.inbound.controller.product.request.CreateProductRequest;
import com.example.samplecommerce.adapter.outbound.jpa.ProductEntity;

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

    public static CreateProductRequest createProductRequestNegativeQuantity() {
        CreateProductRequest request = ProductControllerHelper.validCreateProductRequest();
        request.setQuantity(-1);

        return request;
    }

    public static CreateProductRequest createProductRequestNegativePrice() {
        CreateProductRequest request = ProductControllerHelper.validCreateProductRequest();
        request.setPrice(BigDecimal.valueOf(-1));

        return request;
    }

    public static CreateProductRequest createProductRequestWithBlankField() {
        CreateProductRequest request = ProductControllerHelper.validCreateProductRequest();
        request.setName("");

        return request;
    }

    public static CreateProductRequest createProductRequestWithNullField() {
        CreateProductRequest request = ProductControllerHelper.validCreateProductRequest();
        request.setName(null);

        return request;
    }

    public static ProductEntity productEntity() {
        return ProductEntity.builder()
            .available(true)
            .name("mockProductEntity")
            .price(BigDecimal.valueOf(235.23))
            .quantity(2)
            .build();
    }
}
