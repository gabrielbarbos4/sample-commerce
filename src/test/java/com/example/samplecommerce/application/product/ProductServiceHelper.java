package com.example.samplecommerce.application.product;

import com.example.samplecommerce.application.domain.Product;

import java.math.BigDecimal;

public class ProductServiceHelper {
    public static Product product() {
        Product product = new Product("mockProduct", BigDecimal.valueOf(25L), true, 25);
        product.setId(1L);

        return product;
    }
}
