package fr.univrouen.delivuniv.services;

import fr.univrouen.delivuniv.constant.SearchOrderEnum;
import fr.univrouen.delivuniv.dto.DeliveryPersonDto;
import fr.univrouen.delivuniv.dto.InsertDeliveryPersonDto;
import fr.univrouen.delivuniv.dto.SearchDeliveryPersonDto;
import fr.univrouen.delivuniv.dto.SearchResultsDto;
import fr.univrouen.delivuniv.entities.DeliveryPersonEntity;
import fr.univrouen.delivuniv.exception.RessourceNotFoundException;
import fr.univrouen.delivuniv.repositories.DeliveryPersonRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeliveryPersonService {
    private final DeliveryPersonRepository deliveryPersonRepository;
    private final ModelMapper mapper;

    public DeliveryPersonDto getById(Long id) {
        var deliveryPerson = deliveryPersonRepository.findById(id);
        return mapper.map(deliveryPerson, DeliveryPersonDto.class);
    }

    public DeliveryPersonDto insert(InsertDeliveryPersonDto deliveryPerson) {
        var deliveryPersonEntity = deliveryPersonRepository.save(mapper.map(deliveryPerson, DeliveryPersonEntity.class));
        return mapper.map(deliveryPersonEntity, DeliveryPersonDto.class);
    }

    public SearchResultsDto<DeliveryPersonDto> search(SearchDeliveryPersonDto model) {
        if (model.getItemsPerPage() == null) model.setItemsPerPage(10);
        if (model.getPage() == null) model.setPage(0);
        if (model.getSearch() == null) model.setSearch("");
        var pageable = Pageable.ofSize(model.getItemsPerPage()).withPage(model.getPage());
        if (model.getStartDate() == null && model.getEndDate() == null) {
            if (model.getOrder() == SearchOrderEnum.ORDER_BY_CREATED_DATE_DESC) {
                return SearchResultsDto.from(deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailableOrderByCreatedAtDesc(model.getSearch(), model.isAvailable(), pageable)
                        .map(deliveryPerson-> mapper.map(deliveryPerson, DeliveryPersonDto.class)));
            }
            return SearchResultsDto.from(deliveryPersonRepository.
                    findAllByNameContainsIgnoreCaseAndAvailableOrderByCreatedAt(model.getSearch(),
                            model.isAvailable(),
                            pageable)
                    .map(deliveryPerson -> mapper.map(deliveryPerson, DeliveryPersonDto.class)));
        } else if (model.getStartDate() == null && model.getEndDate() != null) {
            if (model.getOrder() == SearchOrderEnum.ORDER_BY_CREATED_DATE_DESC) {
                return SearchResultsDto.from(deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsBeforeOrderByCreatedAtDesc(model.getSearch(), model.isAvailable(), model.getEndDate(), pageable)
                        .map(deliveryPerson-> mapper.map(deliveryPerson, DeliveryPersonDto.class)));
            }
            return SearchResultsDto.from(deliveryPersonRepository.
                    findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsBeforeOrderByCreatedAt(model.getSearch(),
                            model.isAvailable(),
                            model.getEndDate(),
                            pageable)
                    .map(deliveryPerson -> mapper.map(deliveryPerson, DeliveryPersonDto.class)));
        } else if (model.getStartDate() != null && model.getEndDate() == null) {
            if (model.getOrder() == SearchOrderEnum.ORDER_BY_CREATED_DATE_DESC) {
                return SearchResultsDto.from(deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsAfterOrderByCreatedAtDesc(model.getSearch(), model.isAvailable(), model.getStartDate(), pageable)
                        .map(deliveryPerson-> mapper.map(deliveryPerson, DeliveryPersonDto.class)));
            }
            return SearchResultsDto.from(deliveryPersonRepository.
                    findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsAfterOrderByCreatedAt(model.getSearch(),
                            model.isAvailable(),
                            model.getStartDate(),
                            pageable)
                    .map(deliveryPerson -> mapper.map(deliveryPerson, DeliveryPersonDto.class)));
        } else {
            if (model.getOrder() == SearchOrderEnum.ORDER_BY_CREATED_DATE_DESC) {
                return SearchResultsDto.from(deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsBetweenOrderByCreatedAtDesc(model.getSearch(), model.isAvailable(), model.getStartDate(), model.getEndDate(), pageable)
                        .map(deliveryPerson-> mapper.map(deliveryPerson, DeliveryPersonDto.class)));
            }
            return SearchResultsDto.from(deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsBetweenOrderByCreatedAt(model.getSearch(), model.isAvailable(), model.getStartDate(), model.getEndDate(), pageable)
                    .map(deliveryPerson-> mapper.map(deliveryPerson, DeliveryPersonDto.class)));
        }
    }
    public DeliveryPersonDto update(Long id, InsertDeliveryPersonDto model)  {
            var deliveryPersonEntity = deliveryPersonRepository.findById(id);
        if (deliveryPersonEntity.isEmpty()) {
            throw new RessourceNotFoundException();
        }
        deliveryPersonEntity.get().setName(model.getName());
        deliveryPersonEntity.get().setAvailable(model.isAvailable());
        return mapper.map(deliveryPersonRepository.save(deliveryPersonEntity.get()), DeliveryPersonDto.class);

    }
    public void delete(DeliveryPersonDto person) {
        deliveryPersonRepository.deleteById(person.getId());
    }
}
