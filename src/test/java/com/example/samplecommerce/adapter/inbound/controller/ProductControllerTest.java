package com.example.samplecommerce.adapter.inbound.controller;

import com.example.samplecommerce.adapter.IntegrationTest;
import com.example.samplecommerce.adapter.inbound.controller.product.request.CreateProductRequest;
import com.example.samplecommerce.adapter.inbound.controller.product.response.PageableProductResponse;
import com.example.samplecommerce.adapter.inbound.controller.product.response.ProductResponse;
import com.example.samplecommerce.adapter.outbound.jpa.ProductEntity;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ProductControllerTest extends IntegrationTest {
    private static final String PRODUCT_URL = "/v1/product";

    @Autowired
    private TransactionTemplate transactionTemplate;

    static List<CreateProductRequest> invalidCreateProductRequestArgs() {
        return List.of(
            ProductControllerHelper.createProductRequestWithNullField(),
            ProductControllerHelper.createProductRequestWithBlankField(),
            ProductControllerHelper.createProductRequestNegativeQuantity(),
            ProductControllerHelper.createProductRequestNegativePrice()
        );
    }

    @Test
    @DisplayName("Given valid CreateProductRequest | When create is executed | Then return the created product")
    void t1() throws Exception {
        // arrange
        final CreateProductRequest validCreateProductRequest = ProductControllerHelper.validCreateProductRequest();
        final RequestBuilder request = post(PRODUCT_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(validCreateProductRequest));

        // act
        final MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        final ProductResponse productResponse = objectMapper.readValue(response.getContentAsString(), ProductResponse.class);

        // assert
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON.toString(), response.getContentType());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(@NotNull TransactionStatus status) {
                final Optional<ProductEntity> actualOptionalProduct = productRepository.findById(productResponse.getId());

                assertTrue(actualOptionalProduct.isPresent());

                final ProductEntity actualProduct = actualOptionalProduct.get();

                assertEquals(actualProduct.getQuantity(), validCreateProductRequest.getQuantity());
                assertEquals(actualProduct.getPrice(), validCreateProductRequest.getPrice());
                assertEquals(actualProduct.getName(), validCreateProductRequest.getName());
                assertEquals(actualProduct.isAvailable(), validCreateProductRequest.isAvailable());
            }
        });
    }

    @ParameterizedTest
    @DisplayName("Given invalid CreateProductRequest | When create is executed | then return BAD_REQUEST")
    @MethodSource("invalidCreateProductRequestArgs")
    void t2(CreateProductRequest invalidCreateProductRequest) throws Exception {
        // arrange
        RequestBuilder request = createDefaultPostRequestBuilder(invalidCreateProductRequest);

        // act - Assert
        mockMvc.perform(request).andExpect(status().isBadRequest());
    }

    private RequestBuilder createDefaultPostRequestBuilder(Object request) throws Exception {
        return post(PRODUCT_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request));
    }

    @Test
    @DisplayName("Given valid id | When getProductByid executed | Then return productResponse")
    void t3() throws Exception {
        // Arrange
        final RequestBuilder createRequest = post(PRODUCT_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(ProductControllerHelper.validCreateProductRequest()));
        final MockHttpServletResponse createResponse = mockMvc.perform(createRequest).andReturn().getResponse();
        final ProductResponse createdProduct = objectMapper.readValue(createResponse.getContentAsString(), ProductResponse.class);

        RequestBuilder request = get(PRODUCT_URL + "/" + createdProduct.getId());

        // Act
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        ProductResponse productResponse = objectMapper.readValue(response.getContentAsString(), ProductResponse.class);

        // Assert
        assertNotNull(productResponse);
        assertEquals(productResponse.getId(), createdProduct.getId());
    }

    @Test
    @DisplayName("Given page and pageSize parameters | When getProductList")
    public void t4() throws Exception {
        // Arrange
        final String page = "0", pageSize = "5";
        final int totalItems = 6;
        final RequestBuilder request = get(PRODUCT_URL)
            .param("page", page)
            .param("pageSize", pageSize);
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(@NotNull TransactionStatus status) {
                for (int i = 0; i < totalItems; i++) {
                    productRepository.save(ProductControllerHelper.productEntity());
                }
            }
        });

        // Act
        final MockHttpServletResponse response = mockMvc.perform(request)
            .andExpect(status().isOk())
            .andReturn().getResponse();
        final PageableProductResponse pageableProductResponse = objectMapper.readValue(response.getContentAsString(), PageableProductResponse.class);

        // Assert
        assertThat(pageableProductResponse.getProducts().size()).isEqualTo(Integer.parseInt(pageSize));
        assertThat(pageableProductResponse.getPageNumber()).isEqualTo(Integer.parseInt(page));
        assertThat(pageableProductResponse.getTotalItems()).isEqualTo(totalItems);
        assertThat(pageableProductResponse.getTotalPages()).isEqualTo(2);
        assertThat(pageableProductResponse.getProducts().get(0)).isInstanceOf(ProductResponse.class);
    }
}
