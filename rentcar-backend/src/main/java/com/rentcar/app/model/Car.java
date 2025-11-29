package com.rentcar.app.model;

import jakarta.persistence.*;

/**
 * Represents a car in the rental system.
 * This class is a JPA entity that maps to the "CAR" table in the database.
 */
@Entity
@Table(name = "CAR")
public class Car {

    /**
     * The unique identifier for the car.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The agent or agency that owns the car.
     */
    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent agent;

    /**
     * The license plate number of the car. This is a crucial field for AI validation.
     */
    @Column(name = "plate_number", unique = true, nullable = false)
    private String plateNumber;

    /**
     * The type of car (e.g., Sedan, SUV).
     */
    private String type;

    /**
     * The brand of the car (e.g., Toyota, Ford).
     */
    private String brand;

    /**
     * The model of the car (e.g., Corolla, F-150).
     */
    private String model;

    /**
     * The color of the car.
     */
    private String color;

    /**
     * The year the car was manufactured.
     */
    @Column(name = "manufacture_year")
    private Integer manufactureYear;

    /**
     * The gear type of the car (e.g., Auto, Manual).
     */
    @Column(name = "gear_type")
    private String gearType;

    /**
     * The current status of the car (e.g., Available, In_Use).
     */
    private String status;

    /**
     * Default constructor for JPA.
     */
    public Car() {
    }

    /**
     * Constructs a new Car with the specified details.
     * @param agent The owning agent.
     * @param plateNumber The license plate number.
     * @param type The car type.
     * @param brand The car brand.
     * @param model The car model.
     * @param color The car color.
     * @param manufactureYear The year of manufacture.
     * @param gearType The gear type.
     * @param status The current status.
     */
    public Car(Agent agent, String plateNumber, String type, String brand, String model, String color, Integer manufactureYear, String gearType, String status) {
        this.agent = agent;
        this.plateNumber = plateNumber;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.manufactureYear = manufactureYear;
        this.gearType = gearType;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(Integer manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public String getGearType() {
        return gearType;
    }

    public void setGearType(String gearType) {
        this.gearType = gearType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
