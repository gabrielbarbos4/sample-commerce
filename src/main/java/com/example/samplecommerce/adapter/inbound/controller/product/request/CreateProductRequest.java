package com.example.samplecommerce.adapter.inbound.controller.product.request;


import com.example.samplecommerce.application.domain.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CreateProductRequest {
    @NotNull(message = "Product name cannot be null")
    @NotBlank
    private String name;

    @NotNull(message = "Product price cannot be null")
    private BigDecimal price;

    @NotNull(message = "Product quantity cannot be null")
    private Integer quantity;

    @NotNull(message = "Product availability cannot be null")
    private boolean available;

    public Product toDomain() {
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        p.setQuantity(quantity);
        p.setAvailable(available);

        return p;
    }
}
