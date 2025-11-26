package com.rentcar.app.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for carrying user login credentials.
 * This class is used to transfer the username and password from the client to the server during the authentication process.
 * The @Data annotation from Lombok is used to automatically generate getters, setters,
 * toString, equals, and hashCode methods.
 */
@Data
public class LoginRequest {

    /**
     * The username, which is the user's email address.
     */
    private String username;

    /**
     * The user's plain-text password.
     */
    private String password;
}
