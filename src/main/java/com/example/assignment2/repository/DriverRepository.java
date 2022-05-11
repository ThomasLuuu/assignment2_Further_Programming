package com.example.assignment2.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.assignment2.entity.Driver;
import java.util.Optional;
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findById(Long id);


}

