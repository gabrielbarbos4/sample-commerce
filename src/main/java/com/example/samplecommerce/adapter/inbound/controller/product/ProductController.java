package com.example.samplecommerce.adapter.inbound.controller.product;


import com.example.samplecommerce.adapter.inbound.controller.product.request.CreateProductRequest;
import com.example.samplecommerce.adapter.inbound.controller.product.response.ProductResponse;
import com.example.samplecommerce.application.domain.PageableProduct;
import com.example.samplecommerce.application.ports.inbound.ProductInboundPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductInboundPort productInboundPort;

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid CreateProductRequest request) {
        ProductResponse response = ProductResponse
            .fromDomain(productInboundPort.createProduct(CreateProductRequest.toDomain(request)));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageableProduct> list(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size
    ) {
        return ResponseEntity.ok(productInboundPort.getProductList(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ProductResponse.fromDomain(productInboundPort.getProductById(id)));
    }
}
