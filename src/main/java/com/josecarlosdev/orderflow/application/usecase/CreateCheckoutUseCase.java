package com.josecarlosdev.orderflow.application.usecase;

import com.josecarlosdev.orderflow.application.port.OrderRepository;
import com.josecarlosdev.orderflow.application.port.PaymentGateway;
import com.josecarlosdev.orderflow.application.port.ProductRepository;
import com.josecarlosdev.orderflow.application.port.model.CheckoutSession;
import com.josecarlosdev.orderflow.application.usecase.result.CheckoutResult;
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
    private final PaymentGateway paymentGateway;

    public CreateCheckoutUseCase(ProductRepository productRepository,
                                 OrderRepository orderRepository,
                                 PaymentGateway paymentGateway) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.paymentGateway = paymentGateway;
    }

    public CheckoutResult handleOrder(Long productId, int quantity){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        Order order = new Order(product, quantity);
        Order savedOrder = orderRepository.save(order);
        CheckoutSession session = paymentGateway.createCheckoutSession(savedOrder);

        return new CheckoutResult(savedOrder, session);

    }

}
