package com.josecarlosdev.orderflow.domain.exception;

public class ProductNotFounException extends DomainException{
    public ProductNotFounException(Long productId) {
        super("Producto no encontrado: %d".formatted(productId));
    }
}
