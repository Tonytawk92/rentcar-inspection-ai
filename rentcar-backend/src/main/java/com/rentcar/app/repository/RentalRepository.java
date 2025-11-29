package com.rentcar.app.repository;

import com.rentcar.app.model.Rental;
import com.rentcar.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for {@link Rental} entities.
 */
@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    /**
     * Finds a list of rentals for a specific user and status.
     * @param user The user to find rentals for.
     * @param status The status of the rentals to find (e.g., "Active").
     * @return A list of rentals matching the user and status.
     */
    List<Rental> findByUserAndStatus(User user, String status);
}
