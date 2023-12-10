package fr.univrouen.delivuniv.repositories;

import fr.univrouen.delivuniv.entities.DeliveryPersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface DeliveryPersonRepository extends CrudRepository<DeliveryPersonEntity, Long>, PagingAndSortingRepository<DeliveryPersonEntity, Long> {
    Page<DeliveryPersonEntity> findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsBetween(String search, boolean available, Instant startInstant, Instant endInstant, Pageable pageable, Sort sort);
    Page<DeliveryPersonEntity> findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsAfter(String search, boolean available, Instant startInstant, Pageable pageable, Sort sort);
    Page<DeliveryPersonEntity> findAllByNameContainsIgnoreCaseAndAvailableAndCreatedAtIsBefore(String search, boolean available, Instant endInstant, Pageable pageable, Sort sort);
    Page<DeliveryPersonEntity> findAllByNameContainsIgnoreCaseAndAvailable(String search, boolean available, Pageable pageable, Sort sort);
}
