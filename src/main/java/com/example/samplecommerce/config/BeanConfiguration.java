package com.example.samplecommerce.config;

import com.example.samplecommerce.application.ports.inbound.ProductInboundPort;
import com.example.samplecommerce.application.ports.outbound.ProductOutboundPort;
import com.example.samplecommerce.application.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    ProductInboundPort productInboundPort(ProductOutboundPort productOutboundPort) {
        return new ProductService(productOutboundPort);
    }
}
