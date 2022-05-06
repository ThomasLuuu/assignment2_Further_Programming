package com.example.assignment2.entity;

import javax.persistence.*;

@Entity
@Table(name = "Bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private String bookingID;
    @Column( name = "starting_location")
    private String statLocation;
    @Column( name = "ending_location")
    private String endLocation;
    @Column (name = "pick_up_date_time")
    private String pickUpDateTime;
    @Column ( name = "drop_off_date_time")
    private String dropOffDateTime;
    @Column ( name = "distance")
    private Float distance;
    @Column ( name = "invoiceID")
    private String invoiceID;
    @Column ( name = "customer")
    private String customer;
    @Column ( name = "driver")
    private String driver;
    @Column ( name = "total_charge")
    private String totalCharge;

    public Booking(String bookingID, String statLocation, String endLocation, String pickUpDateTime, String dropOffDateTime, Float distance, String invoiceID, String customer, String driver, String totalCharge) {
        this.bookingID = bookingID;
        this.statLocation = statLocation;
        this.endLocation = endLocation;
        this.pickUpDateTime = pickUpDateTime;
        this.dropOffDateTime = dropOffDateTime;
        this.distance = distance;
        this.invoiceID = invoiceID;
        this.customer = customer;
        this.driver = driver;
        this.totalCharge = totalCharge;
    }

    public Booking(String invoiceID, String customer, String driver, String totalCharge) {
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
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

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(String totalCharge) {
        this.totalCharge = totalCharge;
    }

    public Booking() {
    }
}
