package com.example.assignment2.repository;

import com.example.assignment2.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.assignment2.entity.Customer;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long >{
    Optional<Customer> findById(Long id);

    @Query(value = "SELECT * FROM Customer",
            countQuery = "select count(*) from Customer ",
            nativeQuery = true)
    Page<Customer> findAllCustomer(Pageable pageable);

    @Query(value = "select * from Customer c where c.name LIKE :search "+
            "OR c.phone LIKE :search",nativeQuery = true)
    List<Customer> searchCustomer(String search);
}
