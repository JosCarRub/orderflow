package com.josecarlosdev.orderflow.application.port;

public record CheckoutSession(String sessionId, String paymentUrl) {}