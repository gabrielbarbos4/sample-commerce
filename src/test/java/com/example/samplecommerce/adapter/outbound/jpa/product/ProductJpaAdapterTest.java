package com.example.samplecommerce.adapter.outbound.jpa.product;

import com.example.samplecommerce.adapter.outbound.jpa.ProductEntity;
import com.example.samplecommerce.adapter.outbound.jpa.ProductJpaAdapter;
import com.example.samplecommerce.adapter.outbound.jpa.ProductRepository;
import com.example.samplecommerce.application.domain.PageableProduct;
import com.example.samplecommerce.application.domain.Product;
import com.example.samplecommerce.application.exception.ProductNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductJpaAdapterTest {

    @InjectMocks
    private ProductJpaAdapter adapter;

    @Mock
    private ProductRepository repository;

    @Test
    @DisplayName("Given existent id | When getProductById executed | Then return the product")
    public void t1() {
        // arrange
        when(repository.findById(anyLong())).thenReturn(Optional.of(ProductAdapterHelper.mockEntity()));

        // act
        Product product = adapter.getProductById(1L);

        // assert
        assertEquals(product.getId(), 1L);
        assertThat(product)
            .usingRecursiveComparison()
            .isEqualTo(ProductAdapterHelper.getProductByIdReturn());
    }

    @Test
    @DisplayName("Given not existent id | When getProductById executed | Then throws entityNotFoundException")
    public void t2() {
        // arrange
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        // act - Assert
        assertThatThrownBy(() -> adapter.getProductById(1L)).isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    @DisplayName("Given page and pageSize | When getProductList executed | Then return PageableProduct")
    public void t3() {
        // arrange
        int pageSize = 2, page = 2;
        List<ProductEntity> productEntityList = ProductAdapterHelper.productEntityList();
        Page<ProductEntity> pageableEntity = new PageImpl<ProductEntity>(
            productEntityList, PageRequest.of(page, pageSize), productEntityList.size()
        );
        when(repository.findAll(any(PageRequest.class))).thenReturn(pageableEntity);

        // act
        PageableProduct pageableProduct = adapter.getProductList(page, pageSize);

        // assert
        assertNotNull(pageableProduct);
    }
}
