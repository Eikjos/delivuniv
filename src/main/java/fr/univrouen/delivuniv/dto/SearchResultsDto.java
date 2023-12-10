package fr.univrouen.delivuniv.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Builder
@Data
public class SearchResultsDto<T> {

    private List<T> data;
    private int itemsPerPage;
    private long itemCount;
    private int page;
    private int pageCount;

    public static <T> SearchResultsDto<T> from(Page<T> page) {
        return SearchResultsDto.<T>builder()
                .data(page.toList())
                .itemsPerPage(page.getNumberOfElements())
                .itemCount(page.getTotalElements())
                .page(page.getNumber())
                .pageCount(page.getTotalPages())
                .build();
    }
}
