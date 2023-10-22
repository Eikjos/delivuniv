package fr.univrouen.delivuniv.dto;

import fr.univrouen.delivuniv.constant.SearchOrderEnum;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class SearchDeliveryPersonDto {
    private String search;
    private boolean available;
    private Integer itemsPerPage;
    private Integer page;
    private SearchOrderEnum order;
}
