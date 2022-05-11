package com.example.assignment2.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Customer")

public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;
    private String name;
    private String phone;

    @JsonIgnore
    @ManyToMany(targetEntity = Booking.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "Customer_id", referencedColumnName = "customer_id")
    private List<Booking> booking;

    @JsonIgnore
    @ManyToMany(targetEntity = Invoice.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "Customer", referencedColumnName = "name")
    private List<Invoice> invoice;

    public Customer( String name, String phone){
        this.name = name;
        this.phone = phone;
    }
}
