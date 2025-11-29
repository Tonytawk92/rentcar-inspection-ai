package com.rentcar.app.controller;

import com.rentcar.app.model.Car;
import com.rentcar.app.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    /**
     * Retrieves a list of cars based on their status.
     * To get available cars, the frontend should call this endpoint
     * with the query parameter `status=Available`.
     * @param status The status to filter cars by (e.g., "Available", "In_Use").
     * @return A list of cars matching the status.
     */
    @GetMapping("/available")
    public List<Car> getCarsByStatus(@RequestParam String status) {
        return carRepository.findByStatus(status);
    }
}
