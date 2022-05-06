package com.example.assignment2.entity;

import javax.persistence.*;

@Entity
@Table(name = "Drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name ="driverID")
    private String driverID;
    @Column(name = "license_number")
    private String licenseNumber;
    @Column(name = "phone_number")
    private int phoneNumber;
    @Column(name = "rate")
    private String rate;

    public Driver(String driverID, String licenseNumber, int phoneNumber, String rate) {
        this.driverID = driverID;
        this.licenseNumber = licenseNumber;
        this.phoneNumber = phoneNumber;
        this.rate = rate;
    }

    public Driver() {
    }
    public Driver(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
