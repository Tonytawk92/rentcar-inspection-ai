package com.rentcar.app.repository;

import com.rentcar.app.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for {@link Car} entities.
 * This interface provides standard CRUD operations for Car entities and allows
 * for the definition of custom query methods.
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    /**
     * Finds a list of cars by their current status.
     * @param status The status to search for (e.g., "Available", "In_Use").
     * @return A list of cars matching the given status.
     */
    List<Car> findByStatus(String status);
}
