package com.rentcar.app.controller;

import com.rentcar.app.model.Car;
import com.rentcar.app.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing car-related operations.
 */
@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    /**
     * Retrieves a list of cars based on their current status.
     * This endpoint corresponds to CAR-1 in the design document and is used
     * to fetch available cars for rent.
     * <p>
     * Example Usage:
     * {@code GET /api/cars/available?status=Available}
     *
     * @param status The status to filter cars by (e.g., "Available", "In_Use").
     *               This parameter is required.
     * @return A {@link List} of {@link Car} objects matching the specified status.
     */
    @GetMapping("/available")
    public List<Car> getCarsByStatus(@RequestParam String status) {
        return carRepository.findByStatus(status);
    }
}
