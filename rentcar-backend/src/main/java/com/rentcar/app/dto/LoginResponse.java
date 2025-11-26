package com.rentcar.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for carrying the response after a successful login.
 * This class is used to transfer the JWT and user ID from the server to the client.
 * The @Data annotation from Lombok is used to automatically generate getters, setters,
 * toString, equals, and hashCode methods.
 * The @AllArgsConstructor annotation generates a constructor with all fields.
 */
@Data
@AllArgsConstructor
public class LoginResponse {

    /**
     * The JSON Web Token (JWT) generated for the authenticated user.
     */
    private String token;

    /**
     * The unique identifier of the authenticated user.
     */
    private Long userId;
}
