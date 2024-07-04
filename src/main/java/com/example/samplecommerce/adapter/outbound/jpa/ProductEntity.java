package com.example.samplecommerce.adapter.outbound.jpa;
import com.example.samplecommerce.application.domain.Product;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
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

    public ProductEntity() { }

    public ProductEntity(Product product) {
        name = product.getName();
        price = product.getPrice();
        available = product.isAvailable();
        quantity = product.getQuantity();
    }

    public Product toDomain() {
        Product p = new Product();
        p.setAvailable(available);
        p.setPrice(price);
        p.setQuantity(quantity);
        p.setName(name);

        return p;
    }
}
