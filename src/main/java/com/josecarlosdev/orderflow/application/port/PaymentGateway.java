package com.josecarlosdev.orderflow.application.port;

import com.josecarlosdev.orderflow.domain.Order;

public interface PaymentGateway {
    CheckoutSession createCheckoutSession(Order order);
}