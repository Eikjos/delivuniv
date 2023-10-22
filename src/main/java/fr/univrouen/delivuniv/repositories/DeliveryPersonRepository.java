package fr.univrouen.delivuniv.repositories;

import fr.univrouen.delivuniv.entities.DeliveryPersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryPersonRepository extends CrudRepository<DeliveryPersonEntity, Long> {
    Page<DeliveryPersonEntity> findAllByNameContainsIgnoreCaseAndAvailableOrderByCreatedAt(String search, boolean available, Pageable pageable);
    Page<DeliveryPersonEntity> findAllByNameContainsIgnoreCaseAndAvailableOrderByCreatedAtDesc(String search, boolean available, Pageable pageable);

}
