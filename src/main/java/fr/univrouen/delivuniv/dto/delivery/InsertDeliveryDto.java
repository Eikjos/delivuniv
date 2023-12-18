package fr.univrouen.delivuniv.dto.delivery;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class InsertDeliveryDto {
    @NotNull(message="pick up address is required")
    private String pickUpAddress;
    @NotNull(message="delivery address  is required")
    private String deliveryAddress;
    private UUID deliveryTourId;
}
