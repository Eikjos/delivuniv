package fr.univrouen.delivuniv.deliveryperson;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryPersonRepository extends CrudRepository<DeliveryPersonEntity, Long> {
    Page<DeliveryPersonEntity> findAllByNameContainsAndAvailableOrderByCreatedAt(String search, boolean available, Pageable pageable);
    Page<DeliveryPersonEntity> findAllByNameContainsAndAvailableOrderByCreatedAtDesc(String search, boolean available, Pageable pageable);

}
