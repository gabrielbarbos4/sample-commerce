package com.example.samplecommerce.application.domain;

import com.example.samplecommerce.application.exception.InvalidProductException;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private boolean available;
    private Integer quantity;

    public Product() {
    }

    public Product(String name, BigDecimal price, boolean available, Integer quantity) {
        this.name = name;
        this.available = available;
        this.price = price;
        this.quantity = quantity;
    }

    public void validate() {
        if(
            name == null
                || price == null
                || quantity == null
                || name.isBlank()
                || price.compareTo(BigDecimal.ZERO) < 0
                || quantity < 0
        ) {
            throw new InvalidProductException();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
