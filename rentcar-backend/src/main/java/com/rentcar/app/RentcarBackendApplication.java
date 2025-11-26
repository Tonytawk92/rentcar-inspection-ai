package com.rentcar.app;

import com.rentcar.app.model.User;
import com.rentcar.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Main entry point for the Car Rental Spring Boot application.
 */
@SpringBootApplication
public class RentcarBackendApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * The main method, which is the entry point for the application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(RentcarBackendApplication.class, args);
    }

    /**
     * A CommandLineRunner bean that runs on application startup.
     * It creates a default user for testing purposes.
     * @param userRepository The repository for User entities.
     * @return A CommandLineRunner instance.
     */
    @Bean
    public CommandLineRunner loadData(UserRepository userRepository) {
        return (args) -> {
            User user = new User();
            user.setEmail("test@example.com");
            user.setPassword_hash(passwordEncoder.encode("password"));
            userRepository.save(user);
        };
    }
}