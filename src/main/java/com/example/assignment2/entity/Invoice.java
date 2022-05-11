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
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoice_id;
    private Float total_charge;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name ="Customer", referencedColumnName = "name")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name ="Driver", referencedColumnName = "name")
    private Driver driver;

    public Invoice(Long invoice_id, Float total_charge, Customer customer, Driver driver) {
        this.invoice_id = invoice_id;
        this.total_charge = total_charge;
        this.customer = customer;
        this.driver = driver;
    }

}
