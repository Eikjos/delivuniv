package fr.univrouen.delivuniv.dto.deliveryPerson;

import fr.univrouen.delivuniv.entities.DeliveryPersonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
public class DeliveryPersonDto {
    private UUID id;
    private String name;
    private boolean isAvailable;
    private Instant createdAt;
    private Long numberTours;
}
