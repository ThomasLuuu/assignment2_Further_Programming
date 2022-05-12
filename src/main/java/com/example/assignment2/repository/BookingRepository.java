package com.example.assignment2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.assignment2.entity.Booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.crypto.Data;
import java.util.Date;



public interface BookingRepository extends JpaRepository<Booking, Long>{
    @Query(value = "SELECT * FROM Booking",
    countQuery = "select count(*) from Booking ",
    nativeQuery = true)
    Page<Booking> findALlBooking(Pageable pageable);


    List<Booking> findByDateBetween(LocalDate startDate, LocalDate endDate);


}
