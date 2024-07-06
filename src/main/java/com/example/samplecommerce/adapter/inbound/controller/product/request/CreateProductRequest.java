package com.example.samplecommerce.adapter.inbound.controller.product.request;


import com.example.samplecommerce.application.domain.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class CreateProductRequest {
    @NotNull(message = "Product name cannot be null t1")
    @NotBlank
    private String name;

    @NotNull(message = "Product price cannot be null t2")
    private BigDecimal price;

    @NotNull(message = "Product quantity cannot be null t3")
    private Integer quantity;

    @NotNull(message = "Product availability cannot be null t4")
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
