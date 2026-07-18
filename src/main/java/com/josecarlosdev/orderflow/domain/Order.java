package com.josecarlosdev.orderflow.domain;

import com.josecarlosdev.orderflow.domain.exception.InvalidOrderStateException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updatedAt;

    public Order(Product product, int quantity){
        if(quantity <= 0){
            throw new IllegalArgumentException("La cantidad de productos ordenados debe ser siempre mayor a 0");
        }

        this.product = product;
        this.quantity = quantity;
        this.amount = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        this.status = OrderStatus.PENDING;

    }

    private void transition(OrderStatus requiredCurrent, OrderStatus target) {
        if (this.status != requiredCurrent) {
            throw new InvalidOrderStateException(this.id, this.status, target);
        }
        this.status = target;
    }

    public void markPaid() {
        transition(OrderStatus.PENDING, OrderStatus.PAID);
    }

    public void markFulfilled() {
        transition(OrderStatus.PAID, OrderStatus.FULFILLED);
    }

    public void markFailed() {
        transition(OrderStatus.PAID, OrderStatus.FAILED);
    }


}
