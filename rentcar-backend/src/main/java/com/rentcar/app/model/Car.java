package com.rentcar.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CAR")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent agent;

    @Column(name = "plate_number", unique = true, nullable = false)
    private String plateNumber;

    private String type;
    private String brand;
    private String model;
    private String color;
    @Column(name = "manufacture_year")
    private Integer manufactureYear;

    @Column(name = "gear_type")
    private String gearType;

    private String status; // Available/In_Use

    public Car() {
    }

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
