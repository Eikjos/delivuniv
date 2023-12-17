package fr.univrouen.delivuniv.dto.deliveryTour;

import fr.univrouen.delivuniv.dto.deliveryPerson.DeliveryPersonDto;
import fr.univrouen.delivuniv.entities.DeliveryTourEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryTourDto {
    private UUID id;
    private String name;
    private Instant startDate;
    private Instant endDate;
    private DeliveryPersonDto deliveryPerson;
}
