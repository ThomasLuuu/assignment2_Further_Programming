package com.example.assignment2.entity;

import javax.persistence.*;

@Entity
@Table(name = "Invoice")
public class Invoice  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="invoiceID")
    private String invoiceID;
    @Column(name="driverID")
    private String driverID;
    @Column(name ="customerID")
    private String customerID;
    @Column(name ="total_charge")
    private String totalCharge;

    public Invoice() {
    }

    public Invoice(Long id, String invoiceID, String driverID, String customerID, String totalCharge) {
        this.id = id;
        this.invoiceID = invoiceID;
        this.driverID = driverID;
        this.customerID = customerID;
        this.totalCharge = totalCharge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(String totalCharge) {
        this.totalCharge = totalCharge;
    }
}
