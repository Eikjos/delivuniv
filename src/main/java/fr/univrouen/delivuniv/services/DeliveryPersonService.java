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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DeliveryPersonService {
    private final DeliveryPersonRepository deliveryPersonRepository;
    private final ModelMapper mapper;
    private final Validator validator;

    public List<DeliveryPersonEntity> findAll() {
        return (List<DeliveryPersonEntity>) deliveryPersonRepository.findAll();
    }
    public Optional<DeliveryPersonEntity> findById(UUID id) {
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
        Sort sort = model.getAsc() != null && model.getAsc() != "" ? Sort.by(Sort.Direction.ASC, model.getAsc()) : (model.getDesc() != null && model.getDesc() != "" ? Sort.by(Sort.Direction.DESC, model.getDesc()) : null);
        var pageable = PageRequest.of(model.getPage(), model.getItemsPerPage(), sort);
        if (model.getStartDate() == null && model.getEndDate() == null) {
            return deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailable(model.getSearch(), model.isAvailable(), pageable);
        }
        if (model.getStartDate() == null && model.getEndDate() != null) {
            return deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsBefore(model.getSearch(), model.isAvailable(), model.getStartDate(), pageable);
        }
        if (model.getStartDate() != null && model.getEndDate() == null) {
            return deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsAfter(model.getSearch(), model.isAvailable(), model.getStartDate(), pageable);
        }
        return deliveryPersonRepository.findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsBetween(model.getSearch(), model.isAvailable(), model.getStartDate(), model.getEndDate(), pageable);
    }
    public DeliveryPersonEntity update(UUID id, InsertDeliveryPersonDto model)  {
        var violations = validator.validate(model);
        if (!violations.isEmpty()) throw new ValidationException(violations);
        var deliveryPersonEntity = deliveryPersonRepository.findById(id);
        if (deliveryPersonEntity.isEmpty()) {
            throw new RessourceNotFoundException();
        }
        deliveryPersonEntity.get().setName(model.getName());
        deliveryPersonEntity.get().setAvailable(model.getAvailable());
        return deliveryPersonRepository.save(deliveryPersonEntity.get());

    }
    public void delete(DeliveryPersonEntity person) {
        deliveryPersonRepository.deleteById(person.getId());
    }
}
