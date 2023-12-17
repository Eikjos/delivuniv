package fr.univrouen.delivuniv.repositories;

import fr.univrouen.delivuniv.entities.DeliveryPersonEntity;
import fr.univrouen.delivuniv.entities.DeliveryTourEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface DeliveryTourRepository extends CrudRepository<DeliveryTourEntity, UUID>, PagingAndSortingRepository<DeliveryTourEntity, UUID> {
    Page<DeliveryTourEntity> findAllByDeliveryPerson_IdAndStartDateAfter(UUID personId, Instant date, Pageable pageable);
    Page<DeliveryTourEntity> findAllByStartDateAfter(Instant date, Pageable pageable);
    Page<DeliveryTourEntity> findAllByDeliveryPerson_Id(UUID personId, Pageable pageable);
    List<DeliveryTourEntity> findAllByDeliveryPerson_Id(UUID personId);
    Long countAllByDeliveryPerson_Id(UUID personId);
}
