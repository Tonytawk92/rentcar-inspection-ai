package com.rentcar.app.repository;

import com.rentcar.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for {@link User} entities.
 * This interface provides CRUD operations for User entities and allows for custom query methods.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their email address.
     * @param email The email address to search for.
     * @return An {@link Optional} containing the user if found, or an empty Optional otherwise.
     */
    Optional<User> findByEmail(String email);
}
