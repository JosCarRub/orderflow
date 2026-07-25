package com.josecarlosdev.orderflow.application.exception;

public class PaymentGatewayException extends ApplicationException {
    public PaymentGatewayException(String message, Throwable cause) {
        super(message, cause);
    }
}
/*
    se envuelve StripeException en mi excepción para no pasar nada de Stripe hacia la capa de aplicación,
    pero paso la causa original para no perder la traza
 */