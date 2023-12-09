package fr.univrouen.delivuniv.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Data
@Table(name = "DeliveryTour")
public class DeliveryTourEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Instant StartDate;
    private Instant EndDate;
    @ManyToOne(optional = true)
    private DeliveryPersonEntity deliveryPerson;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;
}
