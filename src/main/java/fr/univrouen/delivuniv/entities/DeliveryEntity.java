package fr.univrouen.delivuniv.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "delivery")
@Data
public class DeliveryEntity {
    @Id
    @UuidGenerator
    private UUID id;
    private String pickUpAddress;
    private String deliveryAddress;
    @ManyToOne
    private DeliveryTourEntity deliveryTour;
}
