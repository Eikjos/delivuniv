package fr.univrouen.delivuniv.entities;

import fr.univrouen.delivuniv.services.DeliveryTourService;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "DeliveryPerson")
public class DeliveryPersonEntity {

    @Id
    @UuidGenerator
    private UUID id;
    private String name;
    private boolean available;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;
    @OneToMany(mappedBy = "deliveryPerson")
    private List<DeliveryTourEntity> deliveryTours;
}
