package com.rentcar.app.model;

import jakarta.persistence.*;
import java.time.LocalDateTime; // Use LocalDateTime

/**
 * Represents a single rental transaction in the system.
 * This class is a JPA entity that maps to the "RENTAL" table in the database,
 * linking a user to a car for a specific period.
 */
@Entity
@Table(name = "RENTAL")
public class Rental {

    /**
     * The unique identifier for the rental transaction.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user who rented the car.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The car being rented.
     */
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    /**
     * The timestamp when the rental period started.
     */
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate; // Changed to LocalDateTime

    /**
     * The timestamp when the rental period ended. Can be null for active rentals.
     */
    @Column(name = "end_date")
    private LocalDateTime endDate; // Changed to LocalDateTime

    /**
     * A JSON or Markdown report of the final damage assessment from the AI service.
     */
    @Lob
    @Column(name = "ai_report")
    private String aiReport;

    /**
     * The current status of the rental (e.g., Active, Completed).
     */
    private String status;

    /**
     * Default constructor for JPA.
     */
    public Rental() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getAiReport() {
        return aiReport;
    }

    public void setAiReport(String aiReport) {
        this.aiReport = aiReport;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
