package fr.univrouen.delivuniv.dto.deliveryTour;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
public class InsertDeliveryTourDto {
    @NotNull(message = "name field is required")
    private String name;
    @NotNull(message = "startDate field is required")
    private Instant startDate;
    @NotNull(message = "endDate field is required")
    private Instant endDate;
    private Long deliveryPersonId;
}
