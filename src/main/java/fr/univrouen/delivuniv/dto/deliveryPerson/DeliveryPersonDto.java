package fr.univrouen.delivuniv.dto.deliveryPerson;

import lombok.Data;

import java.time.Instant;

@Data
public class DeliveryPersonDto {
    private Long id;
    private String name;
    private boolean isAvailable;
    private Instant createdAt;
}
