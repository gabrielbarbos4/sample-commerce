package com.example.samplecommerce.application.product;

import com.example.samplecommerce.application.domain.PageableProduct;
import com.example.samplecommerce.application.domain.Product;
import com.example.samplecommerce.application.exception.InvalidProductException;
import com.example.samplecommerce.application.ports.outbound.ProductOutboundPort;
import com.example.samplecommerce.application.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductOutboundPort productOutboundPort;

    @Test
    @DisplayName("Given Valid Product | When createProduct executed | Then returns the created product")
    public void t1() {
        // Arrange
        Product requestProduct = ProductServiceHelper.product();
        requestProduct.setId(null);
        when(productOutboundPort.saveProduct(requestProduct)).thenReturn(ProductServiceHelper.product());

        // Act
        Product product = productService.createProduct(requestProduct);

        // Assert
        assertNotNull(product.getId());
        assertThat(product)
            .usingRecursiveComparison()
            .ignoringFields("id")
            .isEqualTo(requestProduct);
    }

    @Test
    @DisplayName("Given existent product id | When getProductById executed | Then return the product")
    public void t2() {
        // Arrange
        when(productOutboundPort.getProductById(anyLong())).thenReturn(ProductServiceHelper.product());

        // Act
        Product product = productService.getProductById(1L);

        // Assert
        assertThat(product).isInstanceOf(Product.class);
    }

    @ParameterizedTest
    @DisplayName("Given invalid Product | When createProduct executed | Then throws invalidProductException")
    @MethodSource("invalidProductArgs")
    public void t3(Product product) {
        // act - assert
        assertThatThrownBy(() -> productService.createProduct(product)).isInstanceOf(InvalidProductException.class);
    }


    @Test
    @DisplayName("Given page and pageSize | When getProductList executed | Then return a pageableProduct")
    public void t4() {
        // arrange
        when(productOutboundPort.getProductList(anyInt(), anyInt())).thenReturn(new PageableProduct());

        // act - assert
        assertNotNull(productService.getProductList(5, 5));
    }

    @Test
    @DisplayName("Given existent product id | When getProductById executed | Then return the product")
    public void t5() {
        // Arrange
        when(productOutboundPort.getProductById(anyLong())).thenReturn(ProductServiceHelper.product());

        // Act
        Product product = productService.getProductById(1L);

        // Assert
        assertThat(product).isInstanceOf(Product.class);
    }

    static List<Product> invalidProductArgs() {
        return ProductServiceHelper.invalidProducts();
    }
}
