package com.josecarlosdev.orderflow.application.usecase.result;

import com.josecarlosdev.orderflow.application.port.model.CheckoutSession;
import com.josecarlosdev.orderflow.domain.Order;

public record CheckoutResult(Order order, CheckoutSession session)  {
}
