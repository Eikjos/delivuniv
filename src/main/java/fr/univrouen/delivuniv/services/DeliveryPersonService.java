package fr.univrouen.delivuniv.services;

import fr.univrouen.delivuniv.dto.deliveryPerson.DeliveryPersonDto;
import fr.univrouen.delivuniv.dto.deliveryPerson.InsertDeliveryPersonDto;
import fr.univrouen.delivuniv.dto.deliveryPerson.SearchDeliveryPersonDto;
import fr.univrouen.delivuniv.dto.deliveryPerson.SearchResultsDto;
import fr.univrouen.delivuniv.entities.DeliveryPersonEntity;
import fr.univrouen.delivuniv.exception.RessourceNotFoundException;
import fr.univrouen.delivuniv.repositories.DeliveryPersonRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
            if (model.getAsc() != null && model.getAsc() != "") {
                return SearchResultsDto.from(deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailable(model.getSearch(), model.isAvailable(), pageable, Sort.by(Sort.Direction.ASC, model.getAsc()))
                        .map(deliveryPerson -> mapper.map(deliveryPerson, DeliveryPersonDto.class)));
            }
            return SearchResultsDto.from(deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailable(model.getSearch(), model.isAvailable(), pageable, Sort.by(Sort.Direction.DESC, model.getDesc()))
                    .map(deliveryPerson -> mapper.map(deliveryPerson, DeliveryPersonDto.class)));
        }
        if (model.getStartDate() == null && model.getEndDate() != null) {
            if (model.getAsc() != null && model.getAsc() != "") {
                return SearchResultsDto.from(deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsBefore(model.getSearch(), model.isAvailable(), model.getStartDate(), pageable, Sort.by(Sort.Direction.ASC, model.getAsc()))
                        .map(deliveryPerson -> mapper.map(deliveryPerson, DeliveryPersonDto.class)));
            }
            return SearchResultsDto.from(deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsBefore(model.getSearch(), model.isAvailable(), model.getStartDate(), pageable, Sort.by(Sort.Direction.DESC, model.getDesc()))
                    .map(deliveryPerson -> mapper.map(deliveryPerson, DeliveryPersonDto.class)));
        }
        if (model.getStartDate() != null && model.getEndDate() == null) {
            if (model.getAsc() != null && model.getAsc() != "") {
                return SearchResultsDto.from(deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsAfter(model.getSearch(), model.isAvailable(), model.getStartDate(), pageable, Sort.by(Sort.Direction.ASC, model.getAsc()))
                        .map(deliveryPerson -> mapper.map(deliveryPerson, DeliveryPersonDto.class)));
            }
            return SearchResultsDto.from(deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsAfter(model.getSearch(), model.isAvailable(), model.getStartDate(), pageable, Sort.by(Sort.Direction.DESC, model.getDesc()))
                    .map(deliveryPerson -> mapper.map(deliveryPerson, DeliveryPersonDto.class)));
        }
        if (model.getAsc() != null && model.getAsc() != "") {
            return SearchResultsDto.from(deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsBetween(model.getSearch(), model.isAvailable(), model.getStartDate(), model.getEndDate(), pageable, Sort.by(Sort.Direction.ASC, model.getAsc()))
                    .map(deliveryPerson -> mapper.map(deliveryPerson, DeliveryPersonDto.class)));
        }
        return SearchResultsDto.from(deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsBetween(model.getSearch(), model.isAvailable(), model.getStartDate(), model.getEndDate(), pageable, Sort.by(Sort.Direction.DESC, model.getDesc()))
                .map(deliveryPerson -> mapper.map(deliveryPerson, DeliveryPersonDto.class)));
    }
    public DeliveryPersonDto update(Long id, InsertDeliveryPersonDto model)  {
        var deliveryPersonEntity = deliveryPersonRepository.findById(id);
        if (deliveryPersonEntity.isEmpty()) {
            throw new RessourceNotFoundException();
        }
        if (model.getName() != null) {
            deliveryPersonEntity.get().setName(model.getName());
        }
        if (model.getAvailable() != null) {
            deliveryPersonEntity.get().setAvailable(model.getAvailable());
        }
        return mapper.map(deliveryPersonRepository.save(deliveryPersonEntity.get()), DeliveryPersonDto.class);

    }
    public void delete(DeliveryPersonDto person) {
        deliveryPersonRepository.deleteById(person.getId());
    }
}
