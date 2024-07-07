package com.example.samplecommerce.adapter.inbound.controller.product.request;


import com.example.samplecommerce.application.domain.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class CreateProductRequest {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    private boolean available;

    public static Product toDomain(CreateProductRequest request) {
        Product product = new Product();

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setAvailable(request.isAvailable());

        return product;
    }
}
