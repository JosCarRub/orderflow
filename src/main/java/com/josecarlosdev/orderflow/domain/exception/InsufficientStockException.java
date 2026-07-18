package com.josecarlosdev.orderflow.domain.exception;


public class InsufficientStockException extends DomainException {

    public InsufficientStockException(Long productId, int available, int requested) {
        super("Stock insuficiente para el producto %d: disponible %d, solicitado %d"
                .formatted(productId, available, requested));
    }
}