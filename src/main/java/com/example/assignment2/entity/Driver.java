package com.example.assignment2.entity;

import javax.persistence.*;

@Entity
@Table(name = "Drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="driverID")
    private String driverID;
    @Column(name = "license_number")
    private String licenseNumber;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "rate")
    private double rate;

    public Driver(String driverID, String licenseNumber, String phoneNumber, double rate) {
        this.driverID = driverID;
        this.licenseNumber = licenseNumber;
        this.phoneNumber = phoneNumber;
        this.rate = rate;
    }

    public Driver() {
    }
    public Driver(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
