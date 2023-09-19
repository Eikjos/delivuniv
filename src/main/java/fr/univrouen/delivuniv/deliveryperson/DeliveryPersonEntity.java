package fr.univrouen.delivuniv.deliveryperson;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(name = "DeliveryPerson")
public class DeliveryPersonEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private boolean isAvailable;
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
}
