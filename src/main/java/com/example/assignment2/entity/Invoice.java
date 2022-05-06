package com.example.assignment2.entity;

public class Invoice extends Booking {
    public Invoice() {
    }

    public Invoice(String invoiceID, String customer, String driver, String totalCharge) {
        super(invoiceID, customer, driver, totalCharge);
    }

}
