package com.example.samplecommerce.adapter.outbound.jpa.product;

import com.example.samplecommerce.adapter.outbound.jpa.ProductAdapter;
import com.example.samplecommerce.adapter.outbound.jpa.ProductRepository;
import com.example.samplecommerce.application.domain.Product;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductAdapterTest {

    @InjectMocks
    private ProductAdapter adapter;

    @Mock
    private ProductRepository repository;

    @Test
    public void createProduct_() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        // Act - Assert
        assertThatThrownBy(() -> adapter.getProductById(1L)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void getProduct_withExistentId_thenReturnsProduct() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Optional.of(ProductAdapterHelper.mockEntity()));

        // Act
        Product product = adapter.getProductById(1L);

        // Assert
        assertThat(product)
            .usingRecursiveComparison()
            .isEqualTo(ProductAdapterHelper.getProductByIdReturn());
    }

    @Test
    public void getProduct_withNotExistentId_thenReturnsProduct() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        // Act - Assert
        assertThatThrownBy(() -> adapter.getProductById(1L)).isInstanceOf(EntityNotFoundException.class);
    }
}
