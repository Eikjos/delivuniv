package fr.univrouen.delivuniv.services;

import fr.univrouen.delivuniv.dto.delivery.InsertDeliveryDto;
import fr.univrouen.delivuniv.dto.delivery.SearchDeliveryDto;
import fr.univrouen.delivuniv.entities.DeliveryEntity;
import fr.univrouen.delivuniv.entities.DeliveryTourEntity;
import fr.univrouen.delivuniv.exception.DeliveryNotFoundException;
import fr.univrouen.delivuniv.exception.DeliveryTourNotFoundException;
import fr.univrouen.delivuniv.exception.ValidationException;
import fr.univrouen.delivuniv.repositories.DeliveryRepository;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryTourService deliveryTourService;
    private final Validator validator;
    private final ModelMapper mapper;

    public Page<DeliveryEntity> findAllByDeliveryTour(UUID id, SearchDeliveryDto model) {
        var pageable = PageRequest.of(model.getPage(), model.getItemsPerPage());
        return deliveryRepository.findAllByDeliveryTour_Id(id, pageable);
    }

    public Page<DeliveryEntity> findAll(SearchDeliveryDto model) {
        if (model.getPage() == null) model.setPage(0);
        if (model.getItemsPerPage() == null) model.setItemsPerPage(10);
        var pageable = PageRequest.of(model.getPage(), model.getItemsPerPage());
        return deliveryRepository.findAll(pageable);
    }

    public DeliveryEntity findById(UUID id) {
        return deliveryRepository.findById(id).orElse(null);
    }

    public DeliveryEntity create(InsertDeliveryDto model) {
        var violations = validator.validate(model);
        if (!violations.isEmpty()) throw new ValidationException(violations);

        DeliveryTourEntity deliveryTour = null;
        if (model.getDeliveryTourId() != null) {
            deliveryTour = deliveryTourService.findById(model.getDeliveryTourId());
            if (deliveryTour == null)
                throw new DeliveryTourNotFoundException();
        }
        var delivery = mapper.map(model, DeliveryEntity.class);
        if (deliveryTour != null) {
            delivery.setDeliveryTour(deliveryTour);
        }
        return deliveryRepository.save(delivery);
    }

    public DeliveryEntity update(UUID id, InsertDeliveryDto model) {
        var violations = validator.validate(model);
        if (!violations.isEmpty()) throw new ValidationException(violations);

        DeliveryTourEntity deliveryTour = null;
        if (model.getDeliveryTourId() != null) {
            deliveryTour = deliveryTourService.findById(model.getDeliveryTourId());
            if (deliveryTour == null)
                throw new DeliveryTourNotFoundException();
        }

        var delivery = findById(id);
        if (delivery == null)
            throw new DeliveryNotFoundException();

        delivery.setDeliveryTour(deliveryTour);
        delivery.setPickUpAddress(model.getPickUpAddress());
        delivery.setDeliveryAddress(model.getDeliveryAddress());
        return deliveryRepository.save(delivery);
    }

    public void delete(UUID id) {
        var delivery = findById(id);
        if (delivery == null)
            throw new DeliveryNotFoundException();

        deliveryRepository.delete(delivery);
    }
}
