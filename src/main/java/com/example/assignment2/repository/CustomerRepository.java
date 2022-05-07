package com.example.assignment2.repository;

import com.example.assignment2.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long >{
    Optional<Customer> findById(Long id);
}
