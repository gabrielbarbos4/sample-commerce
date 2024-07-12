package com.example.samplecommerce.adapter.outbound.jpa.product;

import com.example.samplecommerce.adapter.outbound.jpa.ProductEntity;
import com.example.samplecommerce.application.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductAdapterHelper {
    public static ProductEntity mockEntity() {
        return ProductEntity.builder()
            .id(1L)
            .name("mockProductEntity")
            .price(BigDecimal.valueOf(205.23))
            .quantity(5)
            .available(true)
            .build();
    }

    public static Product getProductByIdReturn() {
        Product product = new Product();
        product.setName("mockProductEntity");
        product.setPrice(BigDecimal.valueOf(205.23));
        product.setQuantity(5);
        product.setAvailable(true);
        product.setId(1L);

        return product;
    }

    public static List<ProductEntity> productEntityList() {
        return List.of(
            ProductEntity.builder().id(1L).name("mockProductEntity").price(BigDecimal.valueOf(205.23)).quantity(5).available(true).build(),
            ProductEntity.builder().id(2L).name("mockProductEntity2").price(BigDecimal.valueOf(205.23)).quantity(5).available(true).build()
        );
    }
}
