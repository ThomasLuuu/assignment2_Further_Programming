package com.example.assignment2.entity;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long car_id;
    private String make;
    private String model;
    private String color;
    private String convertible;
    private String rating;
    private String licensePlate;
    private double ratePerKm;
    private boolean status;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Driver", referencedColumnName = "name")
    private Driver driver;

    @JsonIgnore
    @ManyToMany(targetEntity = Booking.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "car_id")
    private List<Booking> booking;
    public Car(String make, String model, String color, String convertible, String rating, String licensePlate, double ratePerKm, boolean status, Driver driver) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.convertible = convertible;
        this.rating = rating;
        this.licensePlate = licensePlate;
        this.ratePerKm = ratePerKm;
        this.status = status;
        this.driver = driver;
    }
}
