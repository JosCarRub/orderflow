package com.josecarlosdev.orderflow.infrastructure.web.dto;

import com.josecarlosdev.orderflow.application.usecase.result.CheckoutResult;
import com.josecarlosdev.orderflow.domain.OrderStatus;

import java.math.BigDecimal;

public record CheckoutResponse(
        Long orderId,
        Long productId,
        int quantity,
        BigDecimal amount,
        OrderStatus status,
        String checkoutUrl
) {
    public static CheckoutResponse from(CheckoutResult result) {
        return new CheckoutResponse(
                result.order().getId(),
                result.order().getProduct().getId(),
                result.order().getQuantity(),
                result.order().getAmount(),
                result.order().getStatus(),
                result.session().paymentUrl()
        );
    }
}