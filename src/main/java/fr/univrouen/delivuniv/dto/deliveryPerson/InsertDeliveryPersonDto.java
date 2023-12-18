package fr.univrouen.delivuniv.dto.deliveryPerson;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InsertDeliveryPersonDto {
    @NotNull(message="name filed is required")
    private String name;
    @NotNull(message="available field is required")
    private Boolean available;
}
