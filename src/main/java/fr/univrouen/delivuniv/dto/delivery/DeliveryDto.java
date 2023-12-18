package fr.univrouen.delivuniv.dto.delivery;

import fr.univrouen.delivuniv.dto.deliveryTour.DeliveryTourDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
public class DeliveryDto {
    private UUID id;
    private String pickUpAddress;
    private String deliveryAddress;
    private DeliveryTourDto deliveryTour;
}
