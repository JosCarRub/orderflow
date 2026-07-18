package com.josecarlosdev.orderflow.domain.exception;

import com.josecarlosdev.orderflow.domain.OrderStatus;

public class InvalidOrderStateException extends DomainException {

    public InvalidOrderStateException(Long orderId, OrderStatus current, OrderStatus target) {
        super("Transición inválida en el pedido %d: no se puede pasar de %s a %s"
                .formatted(orderId, current, target));
    }
}