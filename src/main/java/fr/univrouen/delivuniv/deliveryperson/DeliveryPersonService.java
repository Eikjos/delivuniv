package fr.univrouen.delivuniv.deliveryperson;

import fr.univrouen.delivuniv.constant.SearchOrderEnum;
import fr.univrouen.delivuniv.dto.DeliveryPersonDto;
import fr.univrouen.delivuniv.dto.InsertDeliveryPersonDto;
import fr.univrouen.delivuniv.dto.SearchDeliveryPersonDto;
import fr.univrouen.delivuniv.dto.SearchResultsDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryPersonService {

    private DeliveryPersonRepository deliveryPersonRepository;
    private ModelMapper mapper;

    public DeliveryPersonService(DeliveryPersonRepository deliveryPersonRepository, ModelMapper mapper) {
        this.deliveryPersonRepository = deliveryPersonRepository;
        this.mapper = mapper;
    }

    public DeliveryPersonDto insert(InsertDeliveryPersonDto deliveryPerson) {
        var user = deliveryPersonRepository.save(mapper.map(deliveryPerson, DeliveryPersonEntity.class));
        return mapper.map(user, DeliveryPersonDto.class);
    }

    public SearchResultsDto<DeliveryPersonDto> search(SearchDeliveryPersonDto model) {
        if (model.getItemsPerPage() == null) model.setItemsPerPage(10);
        if (model.getPage() == null) model.setPage(0);
        var pageable = Pageable.ofSize(model.getItemsPerPage()).withPage(model.getPage());
        if (model.getOrder() == SearchOrderEnum.ORDER_BY_CREATED_DATA_DESC) {
            return SearchResultsDto.from(deliveryPersonRepository.findAllByNameContainsAndAvailableOrderByCreatedAtDesc(model.getSearch(), model.isAvailable(), pageable)
                    .map(deliveryPerson-> mapper.map(deliveryPerson, DeliveryPersonDto.class)));
        }
        return SearchResultsDto.from(deliveryPersonRepository.
                findAllByNameContainsAndAvailableOrderByCreatedAt(model.getSearch(),
                        model.isAvailable(),
                        pageable)
                .map(deliveryPerson -> mapper.map(deliveryPerson, DeliveryPersonDto.class)));

    }
    public DeliveryPersonDto update(Long id, InsertDeliveryPersonDto model) {
        var user = deliveryPersonRepository.findById(id);
        if (user.isPresent()) {
            user.get().setName(model.getName());
            user.get().setAvailable(model.isAvailable());
            return mapper.map(deliveryPersonRepository.save(user.get()), DeliveryPersonDto.class);
        }
        return null;
    }
    public void delete(Long id) {
        deliveryPersonRepository.deleteById(id);
    }
}
