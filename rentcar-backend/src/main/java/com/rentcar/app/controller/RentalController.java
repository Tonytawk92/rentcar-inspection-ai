package com.rentcar.app.controller;

import com.rentcar.app.model.Rental;
import com.rentcar.app.model.User;
import com.rentcar.app.repository.RentalRepository;
import com.rentcar.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing rental-related operations.
 */
@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves a list of active rentals for a specific user.
     * This endpoint corresponds to RENTAL-1 in the design document.
     *
     * @param userId The ID of the user whose active rentals are to be fetched.
     * @return A {@link ResponseEntity} containing a {@link List} of {@link Rental} objects.
     *         Returns 404 Not Found if the user does not exist.
     */
    @GetMapping("/active")
    public ResponseEntity<List<Rental>> getActiveRentalsForUser(@RequestParam("user_id") Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Rental> rentals = rentalRepository.findByUserAndStatus(userOptional.get(), "Active");
        return ResponseEntity.ok(rentals);
    }
}
