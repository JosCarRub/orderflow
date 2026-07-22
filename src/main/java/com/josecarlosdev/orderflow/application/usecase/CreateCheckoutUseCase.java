package com.josecarlosdev.orderflow.application.usecase;

import com.josecarlosdev.orderflow.application.port.OrderRepository;
import com.josecarlosdev.orderflow.application.port.ProductRepository;
import com.josecarlosdev.orderflow.domain.Order;
import com.josecarlosdev.orderflow.domain.Product;
import com.josecarlosdev.orderflow.domain.exception.ProductNotFoundException;
/*
    Sin annotation @Service.
    La capa application es agnostica al framework.
    Se creará un bean en config
 */
public class CreateCheckoutUseCase {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public CreateCheckoutUseCase(ProductRepository productRepository, OrderRepository orderRepository){
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public Order handleOrder(Long productId, int quantity){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        Order order = new Order(product, quantity);
        return orderRepository.save(order);
    }

}
