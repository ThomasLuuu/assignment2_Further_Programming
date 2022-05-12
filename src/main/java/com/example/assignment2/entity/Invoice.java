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
@NoArgsConstructor
@Entity
@Table(name = "Invoice")
public class Invoice  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoice_id;
    private double total_charge;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name ="customer_id", nullable = false)
    private Customer customer;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name ="driver_id", nullable = false)
    private Driver driver;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "invoice_id")
    private Booking booking;

    public Invoice(double total_charge, Customer customer, Driver driver) {
        this.total_charge = total_charge;
        this.customer = customer;
        this.driver = driver;
    }

    public double getTotal_charge() {
        return total_charge;
    }

    public void setTotal_charge(double total_charge) {
        this.total_charge = total_charge;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
