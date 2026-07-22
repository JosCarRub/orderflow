package com.josecarlosdev.orderflow.domain.exception;

public class ProductNotFoundException extends DomainException{
    public ProductNotFoundException(Long productId) {
        super("Producto no encontrado: %d".formatted(productId));
    }
}
