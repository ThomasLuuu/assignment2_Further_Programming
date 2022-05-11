package com.example.assignment2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.assignment2.entity.Car;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findById(Long id);
    @Query(value = "SELECT * FROM Car c WHERE c.status = true ",
            nativeQuery = true)
    List<Car> findCarViaStatus();

}
