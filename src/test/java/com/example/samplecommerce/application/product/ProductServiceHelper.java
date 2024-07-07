package com.example.samplecommerce.application.product;

import com.example.samplecommerce.application.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceHelper {
    public static Product product() {
        Product product = new Product("mockProduct", BigDecimal.valueOf(25L), true, 25);
        product.setId(1L);

        return product;
    }

    public static List<Product> invalidProducts() {
        return List.of(
            new Product("", BigDecimal.valueOf(25), true, 25),
            new Product(null, BigDecimal.valueOf(25), true, 25),
            new Product("mockProduct", BigDecimal.valueOf(-1), true, 25),
            new Product("mockProduct", null, true, 25),
            new Product("mockProduct", BigDecimal.valueOf(25), true, -1),
            new Product("mockProduct", BigDecimal.valueOf(-1), true, null)
        );
    }
}
