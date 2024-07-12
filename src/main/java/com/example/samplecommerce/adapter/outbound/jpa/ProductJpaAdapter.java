package com.example.samplecommerce.adapter.outbound.jpa;

import com.example.samplecommerce.application.domain.PageableProduct;
import com.example.samplecommerce.application.domain.Product;
import com.example.samplecommerce.application.exception.ProductNotFoundException;
import com.example.samplecommerce.application.ports.outbound.ProductOutboundPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductJpaAdapter implements ProductOutboundPort {

    private final ProductRepository repository;

    public ProductJpaAdapter(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product saveProduct(Product product) {
        ProductEntity entity = repository.save(ProductEntity.fromDomain(product));

        return ProductEntity.toDomain(entity);
    }

    @Override
    public PageableProduct getProductList(int page, int pageSize) {
        Page<ProductEntity> pageableEntity = repository.findAll(PageRequest.of(page, pageSize));

        PageableProduct pageableProduct = new PageableProduct();
        pageableProduct.setProducts(pageableEntity.getContent().stream().map(ProductEntity::toDomain).toList());
        pageableProduct.setPageNumber(pageableEntity.getPageable().getPageNumber());
        pageableProduct.setPageSize(pageableEntity.getPageable().getPageSize());
        pageableProduct.setTotalItems(pageableEntity.getTotalElements());
        pageableProduct.setTotalPages(pageableEntity.getTotalPages());

        return pageableProduct;
    }


    @Override
    public Product getProductById(Long id) {
        return repository.findById(id)
            .map(ProductEntity::toDomain)
            .orElseThrow(ProductNotFoundException::new);
    }
}
