package com.josecarlosdev.orderflow.application.port.model;

public record CheckoutSession(String sessionId, String paymentUrl) {
}