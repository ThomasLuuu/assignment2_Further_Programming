package com.example.assignment2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.assignment2.entity.Customer;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long >{
    Optional<Customer> findById(Long id);
}
