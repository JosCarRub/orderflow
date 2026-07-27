package com.josecarlosdev.orderflow.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "stripe")
public record StripeProperties(
        String secretKey,
        String successUrl,
        String cancelUrl
) {}