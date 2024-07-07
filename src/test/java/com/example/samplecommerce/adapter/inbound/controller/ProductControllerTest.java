package com.example.samplecommerce.adapter.inbound.controller;

import com.example.samplecommerce.adapter.IntegrationTest;
import com.example.samplecommerce.adapter.inbound.controller.product.request.CreateProductRequest;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
}
