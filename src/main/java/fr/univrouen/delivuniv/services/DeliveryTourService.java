package fr.univrouen.delivuniv.services;

import fr.univrouen.delivuniv.dto.deliveryTour.DeliveryTourDto;
import fr.univrouen.delivuniv.repositories.DeliveryTourRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeliveryTourService {

    private final DeliveryTourRepository deliveryTourRepository;
    private final ModelMapper mapper;

    public DeliveryTourDto getById(Long id) {
        var deliveryTour = deliveryTourRepository.findById(id);
        return mapper.map(deliveryTour, DeliveryTourDto.class);
    }

}
