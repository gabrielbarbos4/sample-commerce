package com.example.samplecommerce.adapter.inbound.controller.product.response;

import com.example.samplecommerce.application.domain.PageableProduct;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PageableProductResponse {
    private int totalPages;
    private Long totalItems;
    private int pageNumber;
    private int pageSize;
    private List<ProductResponse> products;

    public static PageableProductResponse fromDomain(PageableProduct pageableProduct) {
        return PageableProductResponse.builder()
            .totalPages(pageableProduct.getTotalPages())
            .totalItems(pageableProduct.getTotalItems())
            .pageNumber(pageableProduct.getPageNumber())
            .pageSize(pageableProduct.getPageSize())
            .products(pageableProduct.getProducts().stream().map(ProductResponse::fromDomain).toList())
            .build();
    }
}
