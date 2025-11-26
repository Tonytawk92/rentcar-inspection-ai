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

    public Optional<LoginResponse> login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (Exception e) {
            return Optional.empty();
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        Optional<User> user = userRepository.findByEmail(loginRequest.getUsername());
        return user.map(value -> new LoginResponse(jwt, value.getId()));
    }
}
