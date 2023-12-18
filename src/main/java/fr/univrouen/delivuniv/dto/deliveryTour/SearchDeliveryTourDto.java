package fr.univrouen.delivuniv.dto.deliveryTour;

import lombok.Data;

import java.time.Instant;

@Data
public class SearchDeliveryTourDto {
    private Instant date;
    private Integer page;
    private Integer itemsPerPage;
}
