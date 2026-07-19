package com.josecarlosdev.orderflow.infrastructure.persistence;

import com.josecarlosdev.orderflow.application.port.ProductRepository;
import com.josecarlosdev.orderflow.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<Product, Long>, ProductRepository {
}