package com.example.assignment2.entity;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long booking_id;
    @CreationTimestamp
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name ="date",  nullable = false, updatable = false)
    private LocalDate date;
    private String statLocation;
    private String endLocation;
    private String pickUpDateTime;
    private String dropOffDateTime;
    private double distance;

//    @ManyToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name ="customer_id", referencedColumnName = "customer_id")
//    private Customer customer;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name ="car_id", nullable = false)
    private Car car;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "invoice_id")
    private Invoice invoice;

    public Booking( String statLocation, String endLocation, String pickUpDateTime, String dropOffDateTime, double distance, Customer customer, Car car) {
        this.statLocation = statLocation;
        this.endLocation = endLocation;
        this.pickUpDateTime = pickUpDateTime;
        this.dropOffDateTime = dropOffDateTime;
        this.distance = distance;
        this.customer = customer;
        this.car = car;

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatLocation() {
        return statLocation;
    }

    public void setStatLocation(String statLocation) {
        this.statLocation = statLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getPickUpDateTime() {
        return pickUpDateTime;
    }

    public void setPickUpDateTime(String pickUpDateTime) {
        this.pickUpDateTime = pickUpDateTime;
    }

    public String getDropOffDateTime() {
        return dropOffDateTime;
    }

    public void setDropOffDateTime(String dropOffDateTime) {
        this.dropOffDateTime = dropOffDateTime;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


}
