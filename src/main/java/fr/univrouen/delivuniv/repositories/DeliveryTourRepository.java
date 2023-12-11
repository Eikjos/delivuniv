package fr.univrouen.delivuniv.repositories;

import fr.univrouen.delivuniv.entities.DeliveryPersonEntity;
import fr.univrouen.delivuniv.entities.DeliveryTourEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.Instant;
import java.util.List;

public interface DeliveryTourRepository extends CrudRepository<DeliveryTourEntity, Long>, PagingAndSortingRepository<DeliveryTourEntity, Long> {
    Page<DeliveryTourEntity> findAllByDeliveryPerson_IdAndStartDateAfter(Long personId, Instant date, Pageable pageable);
    List<DeliveryTourEntity> findBAllyDeliveryPerson(Long personId);
    Long countAllByDeliveryPerson_Id(Long personId);
}
