package com.josecarlosdev.orderflow.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;


@Entity
@Table(name = "processed_events")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProcessedEvent {

    @Id //jamas @GeneratedValue xq lo va a traer stripe
    @Column(name = "stripe_event_id", nullable = false, updatable = false)
    private String stripeEventId;

    @CreationTimestamp
    @Column(name = "processed_at", nullable = false, updatable = false)
    private Instant processedAt;

    public ProcessedEvent(String stripeEventId) {
        this.stripeEventId = stripeEventId;
    }
}
