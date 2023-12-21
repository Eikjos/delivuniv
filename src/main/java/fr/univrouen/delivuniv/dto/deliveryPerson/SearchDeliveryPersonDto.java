package fr.univrouen.delivuniv.dto.deliveryPerson;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class SearchDeliveryPersonDto {
    private String search;
    private boolean available;
    private Integer itemsPerPage;
    private Integer page;
    private String asc;
    private String desc;
    private Instant startDate;
    private Instant endDate;
}
