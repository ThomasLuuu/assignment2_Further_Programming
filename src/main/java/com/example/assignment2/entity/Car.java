package com.example.assignment2.entity;

import lombok.Getter;

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
    public String make;
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
    @Column(name = "status")
    private boolean status;
    @Column(name = "driver_Id")
    private String driverId;

    public Car(String carID, String make, String model, String color, String convertible, String rating, String licensePlate, double ratePerKm, boolean status, String driverId) {
        this.carID = carID;
        this.make = make;
        this.model = model;
        this.color = color;
        this.convertible = convertible;
        this.rating = rating;
        this.licensePlate = licensePlate;
        this.ratePerKm = ratePerKm;
        this.status = status;
        this.driverId = driverId;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carID='" + carID + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", convertible='" + convertible + '\'' +
                ", rating='" + rating + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", ratePerKm=" + ratePerKm +
                '}';
    }
}
