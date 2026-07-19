package com.josecarlosdev.orderflow.infrastructure.config;

import com.josecarlosdev.orderflow.application.port.OrderRepository;
import com.josecarlosdev.orderflow.application.port.ProductRepository;
import com.josecarlosdev.orderflow.application.usecase.CreateCheckoutUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateCheckoutUseCase createCheckoutUseCase(ProductRepository productRepository,
                                                       OrderRepository orderRepository){
        return new CreateCheckoutUseCase(productRepository,orderRepository);

    }
}
