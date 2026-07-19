package com.josecarlosdev.orderflow.application.port;

import com.josecarlosdev.orderflow.domain.Product;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(Long id);
}