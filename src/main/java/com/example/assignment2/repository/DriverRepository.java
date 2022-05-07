package com.example.assignment2.repository;
import com.example.assignment2.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    Optional<Driver> findById(Integer id);


}

