package com.example.assignment2.entity;

import javax.persistence.*;

@Entity
@Table(name = "Cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="carID")
    private String carID;
    @Column(name = "make")
    private String make;
    @Column(name = "model")
    private String model;
    @Column(name = "color")
    private String color;
    @Column(name = "convertible")
    private String convertible;
    @Column(name = "rating")
    private String rating;
    @Column(name = "license_plate")
    private String licensePlate;
    @Column(name = "rate_per_kilometers") // price per km (dollars)
    private double ratePerKm;

    public Car(String carID, String make, String model, String color, String convertible, String rating, String licensePlate, double ratePerKm) {
        this.carID = carID;
        this.make = make;
        this.model = model;
        this.color = color;
        this.convertible = convertible;
        this.rating = rating;
        this.licensePlate = licensePlate;
        this.ratePerKm = ratePerKm;
    }


    public Car() {
    }
    public Car(Long id) {
        this.id = id;
    }

    public Long getID() {
        return id;
    }

    public void setID(Long id) {
        this.id = id;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
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

    public String getConvertible() {
        return convertible;
    }

    public void setConvertible(String convertible) {
        this.convertible = convertible;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public double getRatePerKm() {
        return ratePerKm;
    }

    public void setRatePerKm(double ratePerKm) {
        this.ratePerKm = ratePerKm;
    }
}
