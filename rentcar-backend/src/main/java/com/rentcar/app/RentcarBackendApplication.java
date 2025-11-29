package com.rentcar.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Car Rental Spring Boot application.
 */
@SpringBootApplication
public class RentcarBackendApplication {

    /**
     * The main method, which is the entry point for the application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(RentcarBackendApplication.class, args);
    }

}