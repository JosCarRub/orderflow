package com.josecarlosdev.orderflow.infrastructure.stripe;

import com.josecarlosdev.orderflow.application.exception.PaymentGatewayException;
import com.josecarlosdev.orderflow.application.port.model.CheckoutSession;
import com.josecarlosdev.orderflow.application.port.PaymentGateway;
import com.josecarlosdev.orderflow.domain.Order;
import com.josecarlosdev.orderflow.infrastructure.config.StripeProperties;
import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway {

    private final StripeClient stripeClient;
    private final StripeProperties properties;

    public StripePaymentGateway(StripeProperties properties) {
        this.properties = properties;
        this.stripeClient = new StripeClient(properties.secretKey());
    }

    @Override
    public CheckoutSession createCheckoutSession(Order order) {
        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(properties.successUrl())
                .setCancelUrl(properties.cancelUrl())
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity((long) order.getQuantity())
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("eur")
                                                .setUnitAmount(toStripeAmount(order.getProduct().getPrice()))
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName(order.getProduct().getName())
                                                                .build())
                                                .build())
                                .build())
                .putMetadata("orderId", String.valueOf(order.getId()))
                .build();

        try {
            Session session = stripeClient.v1().checkout().sessions().create(params);
            return new CheckoutSession(session.getId(), session.getUrl());
        } catch (StripeException e) {
            throw new PaymentGatewayException("No se pudo crear la sesión de pago", e);
        }
    }

    private long toStripeAmount(java.math.BigDecimal price) {
        return price.movePointRight(2).longValueExact();
    }
}