package com.example.samplecommerce.adapter.inbound.controller.product;


import com.example.samplecommerce.adapter.inbound.controller.product.request.CreateProductRequest;
import com.example.samplecommerce.adapter.inbound.controller.product.response.ProductResponse;
import com.example.samplecommerce.application.ports.inbound.ProductInboundPort;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductInboundPort productInboundPort;

    public ProductController(ProductInboundPort productInboundPort) {
        this.productInboundPort = productInboundPort;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid CreateProductRequest createProductRequest) {
        return new ResponseEntity<>(new ProductResponse(productInboundPort.createProduct(createProductRequest.toDomain())), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> list() {
        return ResponseEntity.ok(productInboundPort.getProductList().stream().map(ProductResponse::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new ProductResponse(productInboundPort.getProductById(id)));
    }
}
