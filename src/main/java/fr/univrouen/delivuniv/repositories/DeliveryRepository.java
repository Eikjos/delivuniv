package fr.univrouen.delivuniv.repositories;

import fr.univrouen.delivuniv.entities.DeliveryEntity;
import fr.univrouen.delivuniv.entities.DeliveryPersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface DeliveryRepository extends CrudRepository<DeliveryEntity, UUID>, PagingAndSortingRepository<DeliveryEntity, UUID> {
    Page<DeliveryEntity> findAllByDeliveryTour_Id(UUID id, Pageable pageable);
}
