package com.josecarlosdev.orderflow.infrastructure.persistence;

import com.josecarlosdev.orderflow.application.port.OrderRepository;
import com.josecarlosdev.orderflow.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<Order,Long>, OrderRepository {
}
