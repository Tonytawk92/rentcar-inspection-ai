package com.rentcar.app;

import com.rentcar.app.model.User;
import com.rentcar.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class RentcarBackendApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(RentcarBackendApplication.class, args);
    }

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