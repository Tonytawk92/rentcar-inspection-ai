package com.rentcar.app.controller;

import com.rentcar.app.dto.LoginRequest;
import com.rentcar.app.dto.LoginResponse;
import com.rentcar.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Optional<LoginResponse> loginResponse = authService.login(loginRequest);
        return loginResponse.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }
}
