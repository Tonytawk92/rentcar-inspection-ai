package com.rentcar.app.model;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Represents a rental agent or agency in the system.
 * This class is a JPA entity that maps to the "AGENT" table in the database.
 */
@Entity
@Table(name = "AGENT")
public class Agent {

    /**
     * The unique identifier for the agent.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the agent or agency.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The agent's email address for reports and communication.
     */
    @Column(unique = true)
    private String email;

    /**
     * The agent's contact phone number.
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * The timestamp when the agent was added to the system.
     */
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    /**
     * Default constructor for JPA.
     */
    public Agent() {
    }

    /**
     * Constructs a new Agent with the specified details.
     * @param name The name of the agent.
     * @param email The email of the agent.
     * @param phoneNumber The phone number of the agent.
     * @param createdAt The creation timestamp.
     */
    public Agent(String name, String email, String phoneNumber, Date createdAt) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
