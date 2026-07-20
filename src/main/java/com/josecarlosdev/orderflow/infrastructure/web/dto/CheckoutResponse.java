package com.josecarlosdev.orderflow.infrastructure.web.dto;

import com.josecarlosdev.orderflow.domain.Order;
import com.josecarlosdev.orderflow.domain.OrderStatus;

import java.math.BigDecimal;

public record CheckoutResponse(Long orderId, Long productId, int quantity,
                               BigDecimal amount, OrderStatus status) {
    public static CheckoutResponse from(Order order){
        return new CheckoutResponse(order.getId(), order.getProduct().getId(),
                order.getQuantity(),order.getAmount(),order.getStatus());
    }
}
