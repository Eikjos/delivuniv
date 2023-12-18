package fr.univrouen.delivuniv.services;

import fr.univrouen.delivuniv.dto.deliveryTour.InsertDeliveryTourDto;
import fr.univrouen.delivuniv.dto.deliveryTour.SearchDeliveryTourDto;
import fr.univrouen.delivuniv.entities.DeliveryPersonEntity;
import fr.univrouen.delivuniv.entities.DeliveryTourEntity;
import fr.univrouen.delivuniv.exception.DeliveryPersonHasAlreadyDeliveryTourException;
import fr.univrouen.delivuniv.exception.DeliveryPersonNotFoundException;
import fr.univrouen.delivuniv.exception.DeliveryTourNotFoundException;
import fr.univrouen.delivuniv.exception.ValidationException;
import fr.univrouen.delivuniv.repositories.DeliveryTourRepository;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DeliveryTourService {

    private final DeliveryTourRepository deliveryTourRepository;
    private final DeliveryPersonService deliveryPersonService;
    private final ModelMapper mapper;
    private final Validator validator;

    public DeliveryTourEntity findById(UUID id) {
        var deliveryTour = deliveryTourRepository.findById(id);
        return deliveryTour.orElse(null);
    }

    public Long countAllByDeliveryPerson(UUID personId) {
        return deliveryTourRepository.countAllByDeliveryPerson_Id(personId);
    }
    public Page<DeliveryTourEntity> findAllByDeliveryPerson(UUID personId, SearchDeliveryTourDto model) {
        var pageable = Pageable.ofSize(model.getItemsPerPage()).withPage(model.getPage());
        if (model.getDate() == null) return deliveryTourRepository.findAllByDeliveryPerson_Id(personId, pageable);
        return deliveryTourRepository.findAllByDeliveryPerson_IdAndStartDateAfter(personId,  model.getDate(), pageable);
    }

    public Page<DeliveryTourEntity> findAll(SearchDeliveryTourDto model) {
        if (model.getItemsPerPage() == null) model.setItemsPerPage(10);
        if (model.getPage() == null) model.setPage(0);
        var pageable = Pageable.ofSize(model.getItemsPerPage()).withPage(model.getPage());
        if (model.getDate() == null) {
            return deliveryTourRepository.findAll(pageable);
        }
        return deliveryTourRepository.findAllByStartDateAfter(model.getDate(), pageable);
    }

    public List<DeliveryTourEntity> findAllyDeliveryPerson(UUID personId) {
        return deliveryTourRepository.findAllByDeliveryPerson_Id(personId);
    }

    public void delete(DeliveryTourEntity deliveryTour) {
        deliveryTourRepository.deleteById(deliveryTour.getId());
    }

    public DeliveryTourEntity update(UUID id, InsertDeliveryTourDto model) {
        var validations = validator.validate(model);
        if (!validations.isEmpty()) throw new ValidationException(validations);
        if (model.getStartDate().isAfter(model.getEndDate()))  throw new jakarta.validation.ValidationException("start date must be before the end date");
        var deliveryTour = deliveryTourRepository.findById(id).orElse(null);
        if (deliveryTour == null) {
            throw new DeliveryTourNotFoundException();
        }
        if (deliveryTour.getDeliveryPerson().getId() != model.getDeliveryPersonId()) {
            DeliveryPersonEntity deliveryPerson = validDeliveryPerson(model.getDeliveryPersonId(), model.getStartDate(), model.getEndDate());
            deliveryTour.setDeliveryPerson(deliveryPerson);
        }
        deliveryTour.setName(model.getName());
        deliveryTour.setStartDate(model.getStartDate());
        deliveryTour.setEndDate(model.getEndDate());
        return deliveryTourRepository.save(deliveryTour);
    }
    public DeliveryTourEntity create(InsertDeliveryTourDto model) {
        var validations = validator.validate(model);
        if (!validations.isEmpty()) throw new ValidationException(validations);
        if (model.getStartDate().isAfter(model.getEndDate()))  throw new jakarta.validation.ValidationException("start date must be before the end date");
        DeliveryPersonEntity deliveryPerson = validDeliveryPerson(model.getDeliveryPersonId(), model.getStartDate(), model.getEndDate());
        var deliveryTour = mapper.map(model, DeliveryTourEntity.class);
        if (deliveryPerson != null) {
            deliveryTour.setDeliveryPerson(deliveryPerson);
        }
        return deliveryTourRepository.save(deliveryTour);
    }

    private DeliveryPersonEntity validDeliveryPerson(UUID deliveryPersonId, Instant startDate, Instant endDate) {
        if (deliveryPersonId != null) {
            var deliveryPerson = deliveryPersonService.findById(deliveryPersonId).orElse(null);
            if (deliveryPerson == null) {
                throw new DeliveryPersonNotFoundException();
            }
            var deliveryTourForDeliveryPerson = findAllyDeliveryPerson(deliveryPersonId);
            // si le livreur a déjà une tournée de prévu dans cette période de date.
            var deliveryTourInInterval = deliveryTourForDeliveryPerson.stream().filter(dt ->
                    startDate.isAfter(dt.getStartDate()) && startDate.isBefore(dt.getEndDate())
                            || startDate.isBefore(dt.getStartDate()) && endDate.isAfter(dt.getEndDate())
                            || endDate.isAfter(dt.getStartDate()) && endDate.isBefore(dt.getEndDate())).toList();
            if (deliveryTourInInterval.size() != 0) {
                throw new DeliveryPersonHasAlreadyDeliveryTourException();
            };
            return deliveryPerson;
        }
        return null;
    }
}
