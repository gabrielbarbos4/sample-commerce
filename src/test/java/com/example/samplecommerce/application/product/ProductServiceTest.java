package com.example.samplecommerce.application.product;

import com.example.samplecommerce.application.domain.Product;
import com.example.samplecommerce.application.ports.outbound.ProductOutboundPort;
import com.example.samplecommerce.application.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductOutboundPort productOutboundPort;

    @Test
    public void create_WithProduct_ReturnsProduct() {
        // Arrange
        Product requestProduct = ProductServiceHelper.product();
        when(productOutboundPort.saveProduct(requestProduct)).thenReturn(ProductServiceHelper.product());

        // Act
        Product product = productService.createProduct(requestProduct);

        // Assert
        assertNotNull(product.getId());
        assertThat(product)
            .usingRecursiveComparison()
            //.ignoringFields("id")
            .isEqualTo(ProductServiceHelper.product());
    }

    @Test
    public void getProduct_WithExistentId_ReturnsProduct() {
        // Arrange
        when(productOutboundPort.getProductById(anyLong())).thenReturn(ProductServiceHelper.product());

        // Act
        Product product = productService.getProductById(1L);

        // Assert
        assertThat(product).isInstanceOf(Product.class);
    }
}
