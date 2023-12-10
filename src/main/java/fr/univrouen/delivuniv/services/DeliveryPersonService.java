package fr.univrouen.delivuniv.services;

import fr.univrouen.delivuniv.dto.deliveryPerson.InsertDeliveryPersonDto;
import fr.univrouen.delivuniv.dto.deliveryPerson.SearchDeliveryPersonDto;
import fr.univrouen.delivuniv.entities.DeliveryPersonEntity;
import fr.univrouen.delivuniv.exception.RessourceNotFoundException;
import fr.univrouen.delivuniv.exception.ValidationException;
import fr.univrouen.delivuniv.repositories.DeliveryPersonRepository;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeliveryPersonService {
    private final DeliveryPersonRepository deliveryPersonRepository;
    private final ModelMapper mapper;
    private final Validator validator;

    public Optional<DeliveryPersonEntity> findById(Long id) {
        return deliveryPersonRepository.findById(id);
    }

    public DeliveryPersonEntity insert(InsertDeliveryPersonDto deliveryPerson) {
        var violations = validator.validate(deliveryPerson);
        if (!violations.isEmpty()) throw new ValidationException(violations);
        return deliveryPersonRepository.save(mapper.map(deliveryPerson, DeliveryPersonEntity.class));
    }

    public Page<DeliveryPersonEntity> search(SearchDeliveryPersonDto model) {
        if (model.getItemsPerPage() == null) model.setItemsPerPage(10);
        if (model.getPage() == null) model.setPage(0);
        if (model.getSearch() == null) model.setSearch("");
        var pageable = Pageable.ofSize(model.getItemsPerPage()).withPage(model.getPage());
        if (model.getStartDate() == null && model.getEndDate() == null) {
            if (model.getAsc() != null && model.getAsc() != "") {
                return deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailable(model.getSearch(), model.isAvailable(), pageable, Sort.by(Sort.Direction.ASC, model.getAsc()));
            }
            return deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailable(model.getSearch(), model.isAvailable(), pageable, Sort.by(Sort.Direction.DESC, model.getDesc()));
        }
        if (model.getStartDate() == null && model.getEndDate() != null) {
            if (model.getAsc() != null && model.getAsc() != "") {
                return deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsBefore(model.getSearch(), model.isAvailable(), model.getStartDate(), pageable, Sort.by(Sort.Direction.ASC, model.getAsc()));
            }
            return deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsBefore(model.getSearch(), model.isAvailable(), model.getStartDate(), pageable, Sort.by(Sort.Direction.DESC, model.getDesc()));
        }
        if (model.getStartDate() != null && model.getEndDate() == null) {
            if (model.getAsc() != null && model.getAsc() != "") {
                return deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsAfter(model.getSearch(), model.isAvailable(), model.getStartDate(), pageable, Sort.by(Sort.Direction.ASC, model.getAsc()));
            }
            return deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsAfter(model.getSearch(), model.isAvailable(), model.getStartDate(), pageable, Sort.by(Sort.Direction.DESC, model.getDesc()));
        }
        if (model.getAsc() != null && model.getAsc() != "") {
            return deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsBetween(model.getSearch(), model.isAvailable(), model.getStartDate(), model.getEndDate(), pageable, Sort.by(Sort.Direction.ASC, model.getAsc()));
        }
        return deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsBetween(model.getSearch(), model.isAvailable(), model.getStartDate(), model.getEndDate(), pageable, Sort.by(Sort.Direction.DESC, model.getDesc()));
    }
    public DeliveryPersonEntity update(Long id, InsertDeliveryPersonDto model)  {
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
        return deliveryPersonRepository.save(deliveryPersonEntity.get());

    }
    public void delete(DeliveryPersonEntity person) {
        deliveryPersonRepository.deleteById(person.getId());
    }
}
