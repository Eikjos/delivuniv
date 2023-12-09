package fr.univrouen.delivuniv.dto.deliveryTour;

import fr.univrouen.delivuniv.dto.deliveryPerson.DeliveryPersonDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryTourDto {
    private Long id;
    private String name;
    private DeliveryPersonDto deliveryPersonDto;
}
