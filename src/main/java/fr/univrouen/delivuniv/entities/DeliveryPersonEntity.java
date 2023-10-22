package fr.univrouen.delivuniv.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Data
@Table(name = "DeliveryPerson")
public class DeliveryPersonEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private boolean available;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;
}
