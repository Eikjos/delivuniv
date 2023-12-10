package fr.univrouen.delivuniv.services;

import fr.univrouen.delivuniv.dto.deliveryTour.InsertDeliveryTourDto;
import fr.univrouen.delivuniv.entities.DeliveryPersonEntity;
import fr.univrouen.delivuniv.entities.DeliveryTourEntity;
import fr.univrouen.delivuniv.exception.DeliveryPersonHasAlreadyDeliveryTourException;
import fr.univrouen.delivuniv.exception.DeliveryPersonNotFoundException;
import fr.univrouen.delivuniv.exception.ValidationException;
import fr.univrouen.delivuniv.repositories.DeliveryTourRepository;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeliveryTourService {

    private final DeliveryTourRepository deliveryTourRepository;
    private final DeliveryPersonService deliveryPersonService;
    private final ModelMapper mapper;
    private final Validator validator;

    public DeliveryTourEntity findById(Long id) {
        var deliveryTour = deliveryTourRepository.findById(id);
        return deliveryTour.orElse(null);
    }

    public Long countAllByDeliveryPerson(Long personId) {
        return deliveryTourRepository.countAllByDeliveryPerson_Id(personId);
    }
    public List<DeliveryTourEntity> findBAllyDeliveryPerson(Long personId) {
        return deliveryTourRepository.findAllByDeliveryPerson_Id(personId);
    }

    public void delete(DeliveryTourEntity deliveryTour) {
        deliveryTourRepository.deleteById(deliveryTour.getId());
    }

    public DeliveryTourEntity create(InsertDeliveryTourDto model) {
        var validations = validator.validate(model);
        if (!validations.isEmpty()) throw new ValidationException(validations);
        DeliveryPersonEntity deliveryPerson = null;
        if (model.getDeliveryPersonId() != null) {
            deliveryPerson = deliveryPersonService.findById(model.getDeliveryPersonId()).orElse(null);
            if (deliveryPerson == null) {
                throw new DeliveryPersonNotFoundException();
            }
            var deliveryTourForDeliveryPerson = findBAllyDeliveryPerson(model.getDeliveryPersonId());
            // si le livreur a déjà une tournée de prévu dans cette période de date.
            if (deliveryTourForDeliveryPerson.stream().filter(dt -> dt.getStartDate().isAfter(model.getStartDate()) && dt.getStartDate().isBefore((model.getEndDate()))).count() != 0) {
                throw new DeliveryPersonHasAlreadyDeliveryTourException();
            };
        }


        var deliveryTour = mapper.map(model, DeliveryTourEntity.class);
        if (deliveryPerson != null) {
            deliveryTour.setDeliveryPerson(deliveryPerson);
        }
        return deliveryTourRepository.save(deliveryTour);
    }

}
