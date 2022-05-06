package com.example.assignment2.entity;

import javax.persistence.*;
@Entity
@Table(name = "Customers")

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String customerID;
    @Column( name = "customer name")
    private String customerName;
    @Column( name = "phone number")
    private int phoneNumber;

    public Customer(String customerID, String customerName, int phoneNumber) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
    }

    public Customer() {
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
