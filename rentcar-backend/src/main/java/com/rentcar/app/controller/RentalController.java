package com.rentcar.app.controller;

import com.rentcar.app.dto.CreateRentalRequest;
import com.rentcar.app.model.Car;
import com.rentcar.app.model.Rental;
import com.rentcar.app.model.User;
import com.rentcar.app.repository.CarRepository;
import com.rentcar.app.repository.RentalRepository;
import com.rentcar.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @Autowired
    private CarRepository carRepository;

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

    /**
     * Creates a new rental record.
     * This endpoint corresponds to RENTAL-2 in the design document.
     *
     * @param request The request body containing userId and carId.
     * @return A {@link ResponseEntity} containing the created {@link Rental} object.
     *         Returns 404 Not Found if the user or car does not exist.
     */
    @PostMapping("/start")
    public ResponseEntity<Rental> startRental(@RequestBody CreateRentalRequest request) {
        Optional<User> userOptional = userRepository.findById(request.getUserId());
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Car> carOptional = carRepository.findById(request.getCarId());
        if (carOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Rental rental = new Rental();
        rental.setUser(userOptional.get());
        rental.setCar(carOptional.get());
        rental.setStartDate(LocalDateTime.now());
        rental.setStatus("Active"); // Default status

        Rental savedRental = rentalRepository.save(rental);
        return ResponseEntity.ok(savedRental);
    }
}
