package com.example.assignment2.repository;

import com.example.assignment2.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;



public interface BookingRepository extends JpaRepository<Booking, Integer>{

    Optional<Booking> findById(Integer id);
}
