package com.example.assignment2.repository;

import com.example.assignment2.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.assignment2.entity.Car;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {


    @Query(value = "SELECT * FROM Car c WHERE c.status = true ",
            nativeQuery = true)
    List<Car> findCarViaStatus();

    @Query(value = "SELECT * FROM Car c where c.driver_id IS NULL", nativeQuery = true)
    List<Car> findCarNullDriver();

    @Query(value = "SELECT * FROM Car",
            countQuery = "select count(*) from Car ",
            nativeQuery = true)
    Page<Car> findAllCar(Pageable pageable);

    @Modifying
    @Query(value = "UPDATE Car c set c.driver = :driver where c.car_id = :id")
    Car updateCarDriver(@Param(value = "id") Long id,@Param(value = "driver") Car car);

}
