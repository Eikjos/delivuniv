package fr.univrouen.delivuniv.dto.deliveryTour;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class InsertDeliveryTourDto {
    @NotNull(message = "name is required")
    private String name;
    @NotNull(message = "start date is required")
    private Instant startDate;
    @NotNull(message = "end date is required")
    private Instant endDate;
    private UUID deliveryPersonId;
}
