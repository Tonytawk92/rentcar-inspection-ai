package com.rentcar.app.service;

import com.rentcar.app.dto.LoginRequest;
import com.rentcar.app.dto.LoginResponse;
import com.rentcar.app.model.User;
import com.rentcar.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public Optional<LoginResponse> login(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getUsername());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // No password encryption for now, as requested
            if (user.getPassword_hash().equals(loginRequest.getPassword())) {
                // Dummy token
                String token = "dummy-jwt-token-for-" + user.getEmail();
                return Optional.of(new LoginResponse(token, user.getId()));
            }
        }
        return Optional.empty();
    }
}
