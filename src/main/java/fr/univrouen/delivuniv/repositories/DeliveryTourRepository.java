package fr.univrouen.delivuniv.repositories;

import fr.univrouen.delivuniv.entities.DeliveryPersonEntity;
import fr.univrouen.delivuniv.entities.DeliveryTourEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DeliveryTourRepository extends CrudRepository<DeliveryTourEntity, Long>, PagingAndSortingRepository<DeliveryTourEntity, Long> {
    List<DeliveryTourEntity> findAllByDeliveryPerson_Id(Long personId);
    Long countAllByDeliveryPerson_Id(Long personId);
}
