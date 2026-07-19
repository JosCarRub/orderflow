package com.josecarlosdev.orderflow.application.port;

import com.josecarlosdev.orderflow.domain.Order;

public interface OrderRepository {
    Order save(Order order);
}
