package com.example.samplecommerce.adapter.outbound.jpa.product;

import com.example.samplecommerce.adapter.outbound.jpa.ProductJpaAdapter;
import com.example.samplecommerce.adapter.outbound.jpa.ProductRepository;
import com.example.samplecommerce.application.domain.Product;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductAdapterTest {

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
        assertThatThrownBy(() -> adapter.getProductById(1L)).isInstanceOf(EntityNotFoundException.class);
    }
}
