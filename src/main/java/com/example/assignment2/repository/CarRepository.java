package com.example.assignment2.repository;

import com.example.assignment2.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {


    Optional<Car> findById(Integer id);
}
