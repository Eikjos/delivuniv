package fr.univrouen.delivuniv.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@Table(name = "DeliveryTour")
public class DeliveryTourEntity {
    @Id
    @UuidGenerator
    private UUID id;
    private String name;
    private Instant startDate;
    private Instant endDate;
    @ManyToOne(optional = true)
    private DeliveryPersonEntity deliveryPerson;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;
}
