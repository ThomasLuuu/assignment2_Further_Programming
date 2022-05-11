package com.example.assignment2.repository;

import com.example.assignment2.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.assignment2.entity.Car;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {


    @Query(value = "SELECT * FROM Car c WHERE c.status = true ",
            nativeQuery = true)
    List<Car> findCarViaStatus();

    @Query(value = "SELECT * FROM Car",
            countQuery = "select count(*) from Car ",
            nativeQuery = true)
    Page<Car> findAllCar(Pageable pageable);


}
