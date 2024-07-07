package com.example.samplecommerce.application.service;

import com.example.samplecommerce.application.domain.Product;
import com.example.samplecommerce.application.ports.inbound.ProductInboundPort;
import com.example.samplecommerce.application.ports.outbound.ProductOutboundPort;

import java.util.List;

public class ProductService implements ProductInboundPort {

    private final ProductOutboundPort productOutboundPort;

    public ProductService(ProductOutboundPort productOutboundPort) {
        this.productOutboundPort = productOutboundPort;
    }

    @Override
    public Product createProduct(Product product) {
        product.validate();

        return productOutboundPort.saveProduct(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productOutboundPort.getProductById(id);
    }

    @Override
    public List<Product> getProductList() {
        return productOutboundPort.getProductList();
    }
}
