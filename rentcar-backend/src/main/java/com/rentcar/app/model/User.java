package com.rentcar.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Represents a user in the application.
 * This class is a JPA entity that maps to the "USER_DETAILS" table in the database.
 * The @Data annotation from Lombok is used to automatically generate getters, setters,
 * toString, equals, and hashCode methods.
 */
@Data
@Entity
@Table(name = "USER_DETAILS") // "USER" is often a reserved keyword in SQL databases.
public class User {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user's email address, used for login.
     */
    private String email;

    /**
     * The user's hashed password.
     */
    private String password_hash;
}
