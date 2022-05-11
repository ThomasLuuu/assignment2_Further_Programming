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
@Table(name = "Driver")
public class Driver implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driver_id;
    private String name;
    private String license_id;
    private String phone;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Driver")
    private Car car;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Driver", referencedColumnName = "name")
    private List<Invoice> invoice;

    public Driver(String name, String license_id, String phone) {
        this.name = name;
        this.license_id = license_id;
        this.phone = phone;
    }
}
