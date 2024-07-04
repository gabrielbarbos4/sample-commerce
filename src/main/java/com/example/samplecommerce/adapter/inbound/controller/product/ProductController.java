package com.example.samplecommerce.adapter.inbound.controller.product;


import com.example.samplecommerce.adapter.inbound.controller.product.response.GetProductResponse;
import com.example.samplecommerce.application.domain.Product;
import com.example.samplecommerce.application.ports.inbound.ProductInboundPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductInboundPort productInboundPort;

    public ProductController(ProductInboundPort productInboundPort) {
        this.productInboundPort = productInboundPort;
    }

    @PostMapping
    public Product create() {
        return productInboundPort.createProduct(new Product());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProductResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<>(new GetProductResponse(productInboundPort.getProductById(id)), HttpStatus.OK);
    }
}
