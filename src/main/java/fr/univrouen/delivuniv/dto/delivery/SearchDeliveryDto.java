package fr.univrouen.delivuniv.dto.delivery;

import lombok.Data;

@Data
public class SearchDeliveryDto {
    private Integer itemsPerPage;
    private Integer page;
}
