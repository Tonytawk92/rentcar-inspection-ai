package com.rentcar.app.service;

import com.rentcar.app.dto.LoginRequest;
import com.rentcar.app.dto.LoginResponse;
import com.rentcar.app.model.User;
import com.rentcar.app.repository.UserRepository;
import com.rentcar.app.security.JwtUtil;
import com.rentcar.app.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for handling authentication-related business logic.
 * This service provides methods for user login and JWT generation.
 */
@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    /**
     * Authenticates a user based on their login request and generates a JWT upon successful authentication.
     * @param loginRequest A {@link LoginRequest} object containing the user's username (email) and password.
     * @return An {@link Optional} containing a {@link LoginResponse} with the generated JWT and user ID if authentication is successful,
     *         or an empty Optional if authentication fails.
     */
    public Optional<LoginResponse> login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (Exception e) {
            // Authentication failed
            return Optional.empty();
        }

        // If authentication is successful, generate JWT
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        // Retrieve user details to get the user ID
        Optional<User> user = userRepository.findByEmail(loginRequest.getUsername());
        return user.map(value -> new LoginResponse(jwt, value.getId()));
    }
}
