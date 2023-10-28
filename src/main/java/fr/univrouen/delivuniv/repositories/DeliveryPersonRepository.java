package fr.univrouen.delivuniv.repositories;

import fr.univrouen.delivuniv.entities.DeliveryPersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface DeliveryPersonRepository extends CrudRepository<DeliveryPersonEntity, Long> {
    Page<DeliveryPersonEntity> findAllByNameContainsIgnoreCaseAndAvailableOrderByCreatedAt(String search, boolean available, Pageable pageable);
    Page<DeliveryPersonEntity> findAllByNameContainsIgnoreCaseAndAvailableOrderByCreatedAtDesc(String search, boolean available, Pageable pageable);
    Page<DeliveryPersonEntity> findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsBeforeOrderByCreatedAtDesc(String search, boolean available, Instant endInstant, Pageable pageable);
    Page<DeliveryPersonEntity> findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsAfterOrderByCreatedAtDesc(String search, boolean available, Instant startInstant, Pageable pageable);
    Page<DeliveryPersonEntity> findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsBetweenOrderByCreatedAtDesc(String search, boolean available, Instant startInstant, Instant endInstant, Pageable pageable);
    Page<DeliveryPersonEntity> findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsBeforeOrderByCreatedAt(String search, boolean available, Instant endInstant, Pageable pageable);
    Page<DeliveryPersonEntity> findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsAfterOrderByCreatedAt(String search, boolean available, Instant startInstant, Pageable pageable);
    Page<DeliveryPersonEntity> findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsBetweenOrderByCreatedAt(String search, boolean available, Instant startInstant, Instant endInstant, Pageable pageable);
}
