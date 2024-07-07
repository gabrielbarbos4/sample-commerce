package com.example.samplecommerce.adapter.outbound.jpa;
import com.example.samplecommerce.application.domain.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "available")
    private boolean available;

    @Column(name = "quantity")
    private Integer quantity;

    public static ProductEntity fromDomain(Product product) {
        return ProductEntity.builder()
            .id(product.getId())
            .price(product.getPrice())
            .available(product.isAvailable())
            .quantity(product.getQuantity())
            .name(product.getName())
            .build();
    }

    public static Product toDomain(ProductEntity entity) {
        Product product = new Product();

        product.setAvailable(entity.isAvailable());
        product.setPrice(entity.getPrice());
        product.setQuantity(entity.getQuantity());
        product.setName(entity.getName());
        product.setId(entity.getId());

        return product;
    }
}
